package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RenameMeDTO;
import entities.renameme.RenameMe;
import io.restassured.http.ContentType;
import java.util.List;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class RenameMeResourceTest extends SetupRestTests {

    private static RenameMe r1, r2;

    @BeforeAll
    public static void setUpClass() {
        setupServer();
    }

    @AfterAll
    public static void closeTestServer() {
        shutdownServer();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        r1 = new RenameMe("Some txt", "More text");
        r2 = new RenameMe("aaa", "bbb");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/xxx").then().statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testGetById() throws Exception {
        given()
                .contentType("application/json")
                .pathParam("id", r1.getId())
                .get("/xxx/{id}").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    public void testGetAll() throws Exception {
        List<RenameMeDTO> foundRenameMes;

        foundRenameMes = given()
            .contentType(ContentType.JSON)
            .get("/xxx/").then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode())
            .extract().body().jsonPath().getList("", RenameMeDTO.class);

        assertThat(foundRenameMes, hasItems(
            new RenameMeDTO(r1),
            new RenameMeDTO(r2)
        ));
    }

    @Test
    public void testCreateRenameMe() throws Exception {
        RenameMeDTO requestBody = new RenameMeDTO("create", "me");
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("xxx")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK_200.getStatusCode());    }
}
