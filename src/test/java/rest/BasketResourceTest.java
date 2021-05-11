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

}
