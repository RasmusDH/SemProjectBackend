/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.BasketDTO;
import dtos.BasketItemDTO;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import javax.ws.rs.core.Response;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static rest.SetupRestTests.setupServer;
import static rest.SetupRestTests.shutdownServer;

/**
 *
 * @author peter
 */
public class BasketResourceTest extends SetupRestTests {
    
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
    

//    @Test
//    public void testGetById() {
//        System.out.println("getById");
//        int id = 0;
//        BasketResource instance = new BasketResource();
//        Response expResult = null;
//        Response result = instance.getById(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetAll() {
//        System.out.println("getAll");
//        BasketResource instance = new BasketResource();
//        Response expResult = null;
//        Response result = instance.getAll();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testCreate_0args() {
//        System.out.println("create");
//        BasketResource instance = new BasketResource();
//        Response expResult = null;
//        Response result = instance.create();
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testAddItem() {
        BasketItemDTO requestBody = new BasketItemDTO("Sushi", 1, 2, 3.0);
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/basket/add/user")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode()); 
        }
     

//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        int id = 0;
//        String jsonBody = "";
//        BasketResource instance = new BasketResource();
//        Response expResult = null;
//        Response result = instance.update(id, jsonBody);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//        int id = 0;
//        BasketResource instance = new BasketResource();
//        Response expResult = null;
//        Response result = instance.delete(id);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testCreate_String() {
//        System.out.println("create");
//        String jsonBody = "";
//        BasketResource instance = new BasketResource();
//        Response expResult = null;
//        Response result = instance.create(jsonBody);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
    
}
