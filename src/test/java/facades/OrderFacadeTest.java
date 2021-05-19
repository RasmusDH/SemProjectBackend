/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.Order.ContactInformationDTO;
import dtos.Order.CreditCardDTO;
import dtos.Order.OrderDTO;
import dtos.Order.PaymentDTO;
import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import entities.Role;
import entities.User;
import entities.basket.Basket;
import entities.basket.BasketItem;
import entities.order.OrderEntity;
import entities.order.OrderRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
public class OrderFacadeTest {

    private static EntityManagerFactory emf;
    private static OrderRepository REPO;

    private Basket b1;

    private User user;

    @BeforeEach
    public void setUp() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        REPO = OrderFacade.getInstance(emf);

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

            em.persist(b1);

            b1.addItems(new BasketItem("Sushi Lovers", 2, "Maki", 24, 9.99));
            b1.addItems(new BasketItem("Banana leaf", 1, "Curry", 43, 2.01));
            b1.addItems(new BasketItem("Night shop", 2, "Beer", 44, 3.06));
            b1.addItems(new BasketItem("Pizza 2610", 5, "Margaritha", 11, 5.56));
            b1.addItems(new BasketItem("Sushi Lovers", 2, "Maki2", 24, 9.99));

            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateOrder() {

        ContactInformationDTO contactInformationDTO = new ContactInformationDTO("Hans", "e.mand", "1234", "hansegande", "24.03.02");
        CreditCardDTO creditCardDTO = new CreditCardDTO("11111", "34567", "Hanse", "1234");
        PaymentDTO paymentDTO = new PaymentDTO(user.getUserName(), contactInformationDTO, creditCardDTO, false);

        CountDownLatch lock = new CountDownLatch(1);

        assertDoesNotThrow(() -> {

            REPO.createOrder(paymentDTO);
            lock.await(5000, TimeUnit.MILLISECONDS);
            lock.countDown();
        });

    }

    @Nested
    @DisplayName("get all orders by user")
    class GetAllOrders {

        private OrderEntity order;

        @BeforeEach
        void setUp() {
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                order = new OrderEntity("Bob", "bob@thebuilder.com", "213122", "Bob road", "21.05", 2.0, b1);
                em.persist(order);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        @AfterEach
        void tearDown() {
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.createQuery("DELETE FROM OrderEntity").executeUpdate();
                em.createQuery("DELETE FROM BasketItem").executeUpdate();
                em.createQuery("DELETE FROM Basket").executeUpdate();
                em.createQuery("DELETE FROM Role").executeUpdate();
                em.createNamedQuery("User.deleteAllRows").executeUpdate();
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        @Test
        public void testgetAllOrders() {
            String userName = user.getUserName();
            List<OrderDTO> orders = REPO.getAllOrders(userName);
            assertEquals(1, orders.size());

        }
    }

    @Nested
    @DisplayName("get order by id")
    class GetOrderById {

        private OrderEntity order;

        @BeforeEach
        void setUp() {
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                order = new OrderEntity("Bob", "bob@thebuilder.com", "213122", "Bob road", "21.05", 2.0, b1);
                em.persist(order);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        @AfterEach
        void tearDown() {
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.createQuery("DELETE FROM OrderEntity").executeUpdate();
                em.createQuery("DELETE FROM BasketItem").executeUpdate();
                em.createQuery("DELETE FROM Basket").executeUpdate();
                em.createQuery("DELETE FROM Role").executeUpdate();
                em.createNamedQuery("User.deleteAllRows").executeUpdate();
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }

        @Test
        @DisplayName("should return an orderDTO")
        void shouldReturnAnOrderDto() {
            OrderDTO orderDTO = REPO.getOrderById(order.getId());
            assertNotNull(orderDTO);
        }

        @Test
        @DisplayName("should throw error if given incorrect id")
        void shouldThrowErrorIfGivenIncorrectId() {
            assertThrows(WebApplicationException.class, () -> REPO.getOrderById(1000000L));
        }

        @Test
        @DisplayName("should have order details on it")
        void shouldHaveOrderDetailsOnIt() {
            OrderDTO orderDTO = REPO.getOrderById(order.getId());
            assertEquals(order.getId(), orderDTO.getId());
            assertEquals(order.getAddress(), orderDTO.getAddress());
            assertEquals(order.getDelivery(), orderDTO.getDelivery());
            assertEquals(order.getEmail(), orderDTO.getEmail());
            assertEquals(order.getName(), orderDTO.getName());
            assertEquals(order.getPhone(), orderDTO.getPhone());
            assertEquals(order.getGeneratedBonusPoints(), orderDTO.getGeneratedBonusPoints());
        }

    }

}
