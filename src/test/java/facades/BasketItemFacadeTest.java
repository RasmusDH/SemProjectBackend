/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BasketItemDTO;
import entities.BasketItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
public class BasketItemFacadeTest {
    
     private static final BasketItemFacade facade = BasketItemFacade.getInstance();
    
    
    private static EntityManagerFactory emf;
    BasketItemDTO basketItemDTO;
   
    
    public BasketItemFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
         emf = EMF_Creator.createEntityManagerFactoryForTest();   
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        EntityManager em = emf.createEntityManager();
        
        BasketItem basketItem = new BasketItem("SushiLovers", 10, 100, 100.01);
        basketItemDTO = new BasketItemDTO(basketItem);
        
        try{
            em.getTransaction().begin();
            em.persist(basketItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
    }
    
    
    @AfterEach
    public void tearDown() {
    }

   

    @Test
    public void testCreate() {
        
       // BasketItemDTO actual = facade.create(basketItemDTO);
       assertEquals("SushiLovers", basketItemDTO.getRestaurantName());
        
    }
    
}