package rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import dtos.restaurant.RestaurantDTO;
import io.restassured.http.ContentType;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Disabled
class RestaurantResourceTest extends SetupRestTests {

    @BeforeAll
    static void setUpClass() {
        setupServer();
    }

    @AfterAll
    static void closeTestServer() {
        shutdownServer();
    }

    @Nested
    @DisplayName("get all")
    class GetAll {

        @Test
        @DisplayName("should return a restaurantsDTO with restaurants on it")
        void shouldReturnARestaurantsDtoWithRestaurantsOnIt() {
            List<RestaurantDTO> restaurants = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/restaurants")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("restaurants", RestaurantDTO.class);

            assertFalse(restaurants.isEmpty());
        }

    }

}