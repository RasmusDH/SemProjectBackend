/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import dtos.user.UserDTO;
import entities.basket.Basket;
import entities.basket.BasketItem;
import entities.basket.BasketRepository;
import entities.User;
import entities.basket.ChangeType;
import entities.basket.EditBasket;
import entities.basket.EditBasketType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 * @author peter
 */
public class BasketFacade implements BasketRepository {

    private static BasketFacade instance;
    private static EntityManagerFactory emf;

    public static BasketFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BasketFacade();
        }
        return instance;
    }

    @Override
    public BasketDTO create() throws WebApplicationException {

        EntityManager em = emf.createEntityManager();

        Basket basket = new Basket();

        try {
            em.getTransaction().begin();
            em.persist(basket);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return new BasketDTO(
            basket.getId(),
            new UserDTO(),
            BasketItemDTO.getAllasketItemDtoes(basket.getItems())
        );
    }

    @Override
    public BasketDTO addToBasket(String userName, BasketItemDTO basketItemDTO)
        throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        BasketItem basketItem = new BasketItem(basketItemDTO);

        Basket b;
        
        try {
            em.getTransaction().begin();

            try {
                b = (Basket) em
                    .createQuery(
                        "SELECT b FROM Basket b WHERE b.user.userName = :userName AND b.active = true")
                    .setParameter("userName", userName)
                    .getSingleResult();

                System.out.println("Basket found: " + b);

            } catch (Exception e) {

                User u = em.find(User.class, userName);
                b = new Basket(u);
                System.out.println(u);
                em.persist(b);

                System.out.println("Basket not found" + b);
            }
            
                        
            // Duplicate start        
            // Add to exisiting basketItem or not then new basketItem 
            // Check if item exist restaurantName/dishNumber 
            // if exists + amount (basketItem.getAmount) 
            
            
//            String restaurantName = basketItem.getRestaurantName();
//            
//            try {
//                BasketItem newBasketItem = (BasketItem) em.createQuery(
//                        "SELECT b FROM BasketItem b WHERE b.restaurantName = :restaurantName")
//                    .setParameter("restaurantName", restaurantName)
//                    .getSingleResult();
//                
//            if (basketItem.getDishNumber() == newBasketItem.getDishNumber()) {
//                System.out.println("1 BasketItem amount" + basketItem.getAmount());
//                System.out.println("newBasketItem amount" + newBasketItem.getAmount());
//                
//                basketItem.setAmount(basketItem.getAmount() + newBasketItem.getAmount());
//                
//                System.out.println("2 BasketItem amount" + basketItem.getAmount());
//            
//            } else {
//               
//                }
//            
//            } catch(Exception e) {
//                throw new WebApplicationException ("newBasketItem not working");
//            }            
            
            // Duplicate end        

            b.addItems(basketItem);
            em.getTransaction().commit();

        } finally {
            em.close();
        }
        return new BasketDTO(b);
        
      
    }
    
    public static void main(String[] args) {
        
    }

    @Override
    public BasketDTO getBasket(Long id) throws WebApplicationException {

        EntityManager em = emf.createEntityManager();
        Basket b;

        b = em.find(Basket.class, id);
        if (b == null) {
            throw new WebApplicationException(String.format("Basket with id: (%d) not found", id),
                400);
        }
        em.close();

        return new BasketDTO(b);


    }

    @Override
    public BasketDTO getUsersActiveBasket(String userName) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            Basket basket = em.createQuery(
                "SELECT b FROM Basket b"
                    + " WHERE b.user.userName = :userName"
                    + " AND b.active = true", Basket.class)
                .setParameter("userName", userName)
                .getSingleResult();
            return new BasketDTO(basket);
        } catch (Exception e) {
            throw new WebApplicationException("User has no basket", 400);
        } finally {
            em.close();
        }
    }

    @Override
    public BasketItemDTO editBasket(EditBasketType type, Long itemId) throws WebApplicationException {
        EditBasket editBasket = new EditBasket(emf);
        switch (type) {
            case DELETE:
                return editBasket.deleteItemFromBasket(itemId);
            case INCREMENT:
                return editBasket.changeItemAmount(ChangeType.INCREMENT, itemId);
            case DECREMENT:
                return editBasket.changeItemAmount(ChangeType.DECREMENT, itemId);
            default:
                throw new WebApplicationException("Unknown edit command: " + type.name());
        }
    }

}