package utils;


import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static boolean generateUsers() {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "1234");
    User admin = new User("admin", "1234");
    User both = new User("user_admin", "1234");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    try {
      em.getTransaction().begin();
      Role userRole = new Role("user");
      Role adminRole = new Role("admin");
      user.addRole(userRole);
      admin.addRole(adminRole);
      both.addRole(userRole);
      both.addRole(adminRole);
      em.persist(userRole);
      em.persist(adminRole);
      em.persist(user);
      em.persist(admin);
      em.persist(both);
      em.getTransaction().commit();
      System.out.println("PW: " + user.getUserPass());
      System.out.println("Testing user with OK password: " + user.verifyPassword("test123"));
      System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
      System.out.println("Created TEST Users");
      return true;
    } catch (Exception e) {
      return false;
    } finally {
      em.close();
    }
  }

  public static void main(String[] args) {
    generateUsers();
  }

}
