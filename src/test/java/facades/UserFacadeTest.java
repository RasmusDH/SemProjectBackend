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
         System.out.println("create");
         UserDTO result = facade.create(new UserDTO("Hansemanderne", "2"));
         assertEquals("Hansemanderne", result.getUserName());
         assertNotNull(result);
    }    
}
