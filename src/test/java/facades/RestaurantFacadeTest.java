package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.restaurant.MenuDTO;
import dtos.restaurant.RestaurantDTO;
import dtos.restaurant.RestaurantsDTO;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

@Disabled
class RestaurantFacadeTest {

    private static final RestaurantFacade facade = RestaurantFacade.getInstance();

    @Nested
    @DisplayName("get all menus")
    class GetAllMenus {

        RestaurantsDTO restaurantsDTO;

        @BeforeEach
        void setUp() {
            restaurantsDTO = facade.getAllRestaurants();
        }

        @Test
        @DisplayName("should return a combined menu DTO")
        void shouldReturnACombinedMenuDto() {
            assertNotNull(restaurantsDTO);
        }

        @Test
        @DisplayName("should have a list of restaurants")
        void shouldHaveAListOfRestaurants() {
            assertFalse(restaurantsDTO.getRestaurants().isEmpty());
        }

        @Test
        @DisplayName("should have restaurants with  name and description and menus")
        void shouldHaveRestaurantsWithNameAndDescriptionAndMenus() {
            for (RestaurantDTO restaurantDTO : restaurantsDTO.getRestaurants()) {
                assertNotNull(restaurantDTO.getDescription());
                assertNotNull(restaurantDTO.getName());
                assertFalse(restaurantDTO.getMenus().isEmpty());
            }
        }

        @Test
        @DisplayName("should have menus on each restaurant")
        void shouldHaveMenusOnEachRestaurant() {
            for (RestaurantDTO restaurantDTO : restaurantsDTO.getRestaurants()) {
                for (MenuDTO menuDTO : restaurantDTO.getMenus()) {
                    assertNotNull(menuDTO.getCategory());
                    assertNotNull(menuDTO.getId());
                    assertNotNull(menuDTO.getItemName());
                    assertNotNull(menuDTO.getDescription());
                }
            }
        }

    }


}