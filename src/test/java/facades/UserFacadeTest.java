/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.UserDTO;
import entities.Role;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
// @Disabled
public class UserFacadeTest {
    
    private static EntityManagerFactory emf;
    private static UserFacade facade;
    
    private UserDTO userDTO;
    
    
    @BeforeEach
    public void setUp() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = UserFacade.getUserFacade(emf);
        
        EntityManager em = emf.createEntityManager();
        Role role = new Role("user");
        try {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
        }  finally {       
        }
        em.close();
        
    }
    
    @AfterEach
    public void tearDown() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
          EntityManager em = emf.createEntityManager();
        
           try {
                em.getTransaction().begin();
                em.createQuery("DELETE FROM Role").executeUpdate();
                em.createQuery("DELETE FROM User").executeUpdate();
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        
        
    }

//    @Test
//    public void testGetUserFacade() {
//        System.out.println("getUserFacade");
//        EntityManagerFactory _emf = null;
//        UserFacade expResult = null;
//        UserFacade result = UserFacade.getUserFacade(_emf);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testCreate() {
         System.out.println("create");
         UserDTO result = facade.create(new UserDTO("Hansemanderne", "2"));
         assertEquals("Hansemanderne", result.getUserName());
         assertNotNull(result);
    }

//    @Test
//    public void testGetVeryfiedUser() throws Exception {
//        System.out.println("getVeryfiedUser");
//        String username = "";
//        String password = "";
//        UserFacade instance = null;
//        User expResult = null;
//        User result = instance.getVeryfiedUser(username, password);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
    
}
