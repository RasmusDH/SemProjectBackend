/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.UserDTO;
import entities.Role;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import javax.persistence.EntityManager;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static rest.SetupRestTests.emf;
import static rest.SetupRestTests.setupServer;
import static rest.SetupRestTests.shutdownServer;


/**
 *
 * @author peter
 */
public class UserResourceTest extends SetupRestTests {
    
    @BeforeAll
    static void setUpClass() {
        setupServer();
    }

    @AfterAll
    static void closeTestServer() {
        shutdownServer();
    }
    
     @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Role role = new Role("user");
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Role").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.persist(role);
            em.getTransaction().commit();
        }  finally {       
        }
        em.close();
        
    }
    
    @Test
    public void testCreate() {
        UserDTO requestBody = new UserDTO("Hans", "e.mand");
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/user")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode());
    }
 
}
