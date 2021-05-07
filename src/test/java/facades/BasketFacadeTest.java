/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BasketDTO;
import dtos.BasketItemDTO;
import entities.Basket;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
public class BasketFacadeTest {
    
    private static EntityManagerFactory emf;
    
   
    private static BasketFacade facade;

    @Nested
    @DisplayName("Basket")
    class GetBasket {

    private BasketDTO b1;
    private BasketDTO b2;
    
        
        
    @BeforeEach
    public void setUp() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = BasketFacade.getInstance(emf);
       
        b1 = facade.create();
        b2 = new BasketDTO(new Basket());

    }
    
    @AfterEach
    public void tearDown() {
    }

   
    @Test
    public void testCreate() {
        
        System.out.println("create");
        BasketDTO result = facade.create();
        assertNotNull(result);
        
    }

    @Test
    public void testAddToBasket() {
        
        System.out.println("addToBasket");
        BasketItemDTO basketItemDTO = new BasketItemDTO("PIZZA2610", 1, 2, 3);
        BasketDTO bDto = facade.addToBasket(b1.getId(), basketItemDTO);
        assertEquals(1, bDto.getItems().size());
        assertEquals("PIZZA2610", bDto.getItems().get(0).getRestaurantName());
        
        // Testing exception in case of basket does not exist in database.
        b2.setId(0L);
        assertThrows(WebApplicationException.class, () -> {facade.addToBasket(b2.getId(), basketItemDTO);});

    }
    
    @Test
    public void testGetBasket() {
        
        System.out.println("getBasket");
        
        BasketDTO bDto = facade.getBasket(b1.getId());   
        assertEquals(b1.getId(), bDto.getId());
        
        // Testing exception in case of basket does not exist in database.
        b2.setId(0L);
        assertThrows(WebApplicationException.class, () -> {facade.getBasket(0L);});

    }
    
    }
    
}

