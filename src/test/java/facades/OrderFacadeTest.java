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
import entities.order.OrderRepository;
import java.util.List;
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
            b1.addItems(new BasketItem("Banana leaf",  1, "Curry", 43, 2.01));
            b1.addItems(new BasketItem("Night shop",  2, "Beer", 44, 3.06));
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
        PaymentDTO paymentDTO = new PaymentDTO(user.getUserName(), contactInformationDTO, creditCardDTO);

        assertDoesNotThrow(() -> REPO.createOrder(paymentDTO));

    }
    
    @Test
    public void testGetBasketDTOFromUserName() {
        OrderFacade orderFacade = new OrderFacade();
        String x = "basketUser";
        BasketDTO basketDTO = orderFacade.getBasketDTOFromUserName(x);
        // System.out.println(basketDTO.getItems());
        
    }
    
    @Test
    public void testgetAllOrders() {
       
        String userName = user.getUserName();
        List<OrderDTO> orders = REPO.getAllOrders(userName);
        System.out.println("Orders: " + orders);
    }

}
