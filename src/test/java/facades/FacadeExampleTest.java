package facades;

import dtos.RenameMeDTO;
import entities.renameme.RenameMeRepository;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import entities.renameme.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeExampleTest {

    private static EntityManagerFactory emf;
    private static RenameMeRepository facade;
    public static RenameMe renameMe1, renameMe2;

    public FacadeExampleTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadeExample.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        renameMe1 = new RenameMe("Some txt", "More text");
        renameMe2 = new RenameMe("aaa", "bbb");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
            em.persist(renameMe1);
            em.persist(renameMe2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    void getById() {
        int expected = renameMe1.getId();
        RenameMeDTO actual = facade.getById(expected);
        assertEquals(expected, actual.getId());
    }

    @Test
    void getAll() {
        List<RenameMeDTO> expected = new ArrayList<>();
        expected.add(new RenameMeDTO(renameMe1));
        expected.add(new RenameMeDTO(renameMe2));

        List<RenameMeDTO> actual = facade.getAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void createRenameMe() {
        RenameMeDTO expected = new RenameMeDTO("create", "me");
        RenameMeDTO actual = facade.createRenameMe(expected);
        assertEquals(expected.getDummyStr1(), actual.getDummyStr1());
        assertNotNull(actual.getId());
    }
}
