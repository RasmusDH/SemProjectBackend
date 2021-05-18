/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import entities.basket.Basket;
import entities.Role;
import entities.User;
import entities.basket.BasketItem;
import entities.basket.EditBasketType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import utils.EMF_Creator;

/**
 * @author peter
 */
public class BasketFacadeTest {

    private static EntityManagerFactory emf;
    private static BasketFacade facade;

    @Nested
    @DisplayName("Basket")
    class GetBasket {

        private Basket b1;
        private Basket b2;
        private User user;


        @BeforeEach
        public void setUp() {
            emf = EMF_Creator.createEntityManagerFactoryForTest();
            facade = BasketFacade.getInstance(emf);

            EntityManager em = emf.createEntityManager();

            user = new User("basketUser", "123");
            Role role = new Role("user");
            user.addRole(role);


            try {
                em.getTransaction().begin();
                em.createQuery("DELETE FROM OrderEntity").executeUpdate();
                em.createQuery("DELETE FROM BasketItem").executeUpdate();
                em.createQuery("DELETE FROM Basket").executeUpdate();
                em.createQuery("DELETE FROM Role").executeUpdate();
                em.createNamedQuery("User.deleteAllRows").executeUpdate();
                em.persist(user);

                b1 = new Basket(user);
                b2 = new Basket();
                em.persist(b1);
                em.persist(b2);
                b1.addItems(new BasketItem("Sushi", 2, "Maki", 24, 9.99));
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
            BasketDTO result = facade.create();
            assertNotNull(result);
        }

        @Test
        public void testAddToBasket() {
            BasketItemDTO basketItemDTO = new BasketItemDTO("PIZZA2610", 1, "Margaritha", 2, 3);
            BasketDTO bDto = facade.addToBasket("basketUser", basketItemDTO);
            assertEquals(2, bDto.getItems().size());
            assertEquals("PIZZA2610", bDto.getItems().get(1).getRestaurantName());
        }

        @Test
        public void testGetBasket() {
            BasketDTO bDto = facade.getBasket(b1.getId());
            assertEquals(b1.getId(), bDto.getId());

            // Testing exception in case of basket does not exist in database.
            b2.setId(0L);
            assertThrows(WebApplicationException.class, () -> {
                facade.getBasket(0L);
            });
        }

        @Test
        @DisplayName("get users active basket will return a basket if user has a basket")
        void getUsersActiveBasketWillReturnABasketIfUserHasABasket() {
            BasketDTO basketDTO = facade.getUsersActiveBasket(user.getUserName());
            assertNotNull(basketDTO.getId());
        }
        
        @Test
        public void testGetTotalPrice() {
            BasketFacade facade = new BasketFacade();
            List<BasketItemDTO> list = new ArrayList<>();
            BasketItemDTO one = new BasketItemDTO("PIZZA2610", 1, "Margaritha", 2, 3);
            BasketItemDTO two = new BasketItemDTO("PIZZA2610", 2, "Margaritha", 4, 7);
            list.add(one);
            list.add(two);
                        
            
        }

        @Nested
        @DisplayName("edit basket")
        class editBasket {

            private long oldId;
            private long oldAmount;

            @BeforeEach
            void setUp() {
                oldId = b1.getItems().get(0).getId();
                oldAmount = b1.getItems().get(0).getAmount();
            }

            @Test
            @DisplayName("should increase an items amount given increment")
            void shouldIncreaseAnItemsAmountGivenIncrement() {
                BasketItemDTO edited = facade.editBasket(EditBasketType.INCREMENT, oldId);
                assertEquals(oldAmount, edited.getAmount() - 1);
                assertEquals(oldId, edited.getId());
            }

            @Test
            @DisplayName("should decrement item amount given decrement")
            void shouldDecrementItemAmountGivenDecrement() {
                BasketItemDTO edited = facade.editBasket(EditBasketType.DECREMENT, oldId);
                assertEquals(oldAmount, edited.getAmount() + 1);
                assertEquals(oldId, edited.getId());
            }

            @Test
            @DisplayName("should delete item given delete")
            void shouldDeleteItemGivenDelete() {
                BasketItemDTO deleted = facade.editBasket(EditBasketType.DELETE, oldId);
                assertNotNull(deleted);
                assertFalse(b1.getItems().contains(new BasketItem(deleted)));
            }

        }

    }
}
