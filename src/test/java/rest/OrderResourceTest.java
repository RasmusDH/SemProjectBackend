package rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import dtos.Order.ContactInformationDTO;
import dtos.Order.CreditCardDTO;
import dtos.Order.PaymentDTO;
import entities.Role;
import entities.User;
import entities.basket.Basket;
import entities.basket.BasketItem;
import io.restassured.http.ContentType;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderResourceTest extends SetupRestTests {

    private Basket b1;

    @BeforeAll
    static void setUpClass() {
        setupServer();
    }

    @AfterAll
    static void closeTestServer() {
        shutdownServer();
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM OrderEntity").executeUpdate();
            em.createQuery("DELETE FROM BasketItem").executeUpdate();
            em.createQuery("DELETE FROM Basket").executeUpdate();
            em.createQuery("DELETE FROM Role").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();

            User user = new User("user", "1234");
            Role role = new Role("user");
            em.persist(role);
            em.persist(user);
            user.addRole(role);
            em.getTransaction().commit();

            em.getTransaction().begin();
            b1 = new Basket(user);
            b1.addItems(new BasketItem("Sushi", 2, "Maki", 10, 9.99));
            em.persist(b1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private String login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        return given()
            .contentType("application/json")
            .body(json)
            .when().post("/login")
            .then()
            .extract().path("token");
    }

    @Test
    void create() {
        ContactInformationDTO contactInformationDTO = new ContactInformationDTO(
            "User", "user@user.com", "1231121", "UserRoad", "20.21.12"
        );
        CreditCardDTO creditCardDTO = new CreditCardDTO("12312313", "3211", "UserCard", "213");
        PaymentDTO requestBody = new PaymentDTO("user", contactInformationDTO, creditCardDTO);
        String token = login("user", "1234");
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token", token)
            .body(requestBody)
            .when()
            .post("/order")
            .then()
            .statusCode(200);
    }

    @Test
    void getById() {
    }

}