/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BasketItemDTO;
import entities.BasketItem;
import entities.BasketItemRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public class BasketItemFacade implements BasketItemRepository {
    
    private static BasketItemFacade instance;
    private static EntityManagerFactory emf;

    private BasketItemFacade() {
    }

    public static BasketItemFacade getInstance() {
        if (instance == null) {
            instance = new BasketItemFacade();
        }
        return instance;
    }

    @Override
    public BasketItemDTO create(BasketItemDTO basketItemDTO) throws WebApplicationException {
        
       EntityManager em = emf.createEntityManager();
       
       BasketItem basketItem = new BasketItem(
               basketItemDTO.getRestaurantName(), 
               basketItemDTO.getDishNumber(), 
               basketItemDTO.getAmount(), 
               basketItemDTO.getPrice());
       
       try{
            em.getTransaction().begin();
            em.persist(basketItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return basketItemDTO;
    }
}