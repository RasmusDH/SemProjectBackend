/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BasketDTO;
import dtos.BasketItemDTO;
import entities.Basket;
import entities.BasketItem;
import entities.BasketRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 *
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
        
        try{
           em.getTransaction().begin();
           em.persist(basket);
           em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return new BasketDTO(basket);   
    }

    @Override
    public BasketDTO addToBasket(Long id, BasketItemDTO basketItemDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        BasketItem basketItem = new BasketItem(basketItemDTO);
        
        Basket b;
              
        try{
            em.getTransaction().begin();
            b = em.find(Basket.class, id);
                if (b == null) {
                throw new WebApplicationException(String.format("Basket with id: (%d) not found", id),
                        400);
                }
            b.addItems(basketItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BasketDTO(b);
        
        
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
    
}
