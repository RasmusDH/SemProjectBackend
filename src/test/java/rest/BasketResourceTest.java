/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.basket.BasketItemDTO;

import static io.restassured.RestAssured.given;

import entities.Role;
import entities.User;
import entities.basket.Basket;
import entities.basket.BasketItem;
import io.restassured.http.ContentType;
import javax.persistence.EntityManager;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author peter
 */
public class BasketResourceTest extends SetupRestTests {

    Basket b1;

    public BasketResourceTest() {
    }

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
            b1.addItems(new BasketItem("Sushi", 2, 10, 9.99));
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
    public void testAddItem() {
        String token = login("user", "1234");
        BasketItemDTO requestBody = new BasketItemDTO("Sushi", 1, 2, 3.0);
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token", token)
            .body(requestBody)
            .when()
            .post("/basket/add/user")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    @DisplayName("get users active basket returns a basket")
    void getUsersActiveBasketReturnsABasket() {
        String token = login("user", "1234");
        given()
            .contentType(ContentType.JSON)
            .header("x-access-token", token)
            .when()
            .get("/basket/active")
            .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("get users active basket throws error if no user")
    void getUsersActiveBasketThrowsErrorIfNoUser() {
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/basket/active")
            .then()
            .statusCode(403);
    }

}
