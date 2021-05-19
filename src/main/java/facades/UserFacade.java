package facades;

import dtos.user.UserDTO;
import entities.Role;
import entities.User;
import entities.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade implements UserRepository {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public boolean isValidUserCredentials(UserDTO userDTO) {
        if (userDTO.getUserName() == null) {
            return false;
        }
        if (userDTO.getPassword() == null) {
            return false;
        }
        if (userDTO.getUserName().isEmpty()) {
            return false;
        }
        if (userDTO.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public UserDTO create(UserDTO userDTO) throws WebApplicationException {

        if (!isValidUserCredentials(userDTO)) {
            throw new WebApplicationException("Invalid JSON supplied for signup", 403);
        }

        // We create a User with user 'Role' - not admin.
        EntityManager em = emf.createEntityManager();
        User user = new User(userDTO.getUserName(), userDTO.getPassword());

        try {
            em.getTransaction().begin();
            Role role = em.find(Role.class, "user");
            user.addRole(role);
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException("Username already exist");
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }


    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }
    
    @Override
    public double getBonusPoints(String userName) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, userName);
            if (user == null) {
                throw new WebApplicationException("Unable to find user");
            }
        } finally {
            em.close();
        }
        return user.getBonusPoints();
    }

}
