package facades;

import com.google.gson.Gson;
import dtos.restaurant.RestaurantDTO;
import dtos.restaurant.RestaurantsDTO;
import dtos.restaurant.bananaleaf.BananaLeafDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import utils.HttpUtil;

public class RestaurantFacade {

    private static RestaurantFacade instance;
    private static EntityManagerFactory emf;

    private RestaurantFacade() {
    }

    public static RestaurantFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RestaurantFacade();
        }
        return instance;
    }

    private String[] getRestaurantURLS() {
        String[] urls = {
            "https://api.tobias-z.com/sushi/api/meals/",
            "https://peterrambeckandersen.com/tomcat/Bananaleaf/api/restaurant/"
        };
        return urls;
    }

    public RestaurantsDTO getAllRestaurants() throws WebApplicationException {
        try {
            String[] urls = getRestaurantURLS();
            List<String> jsonDataArray = HttpUtil.fetchMany(urls);
            Gson gson = new Gson();
            BananaLeafDTO bananaLeafDTO = gson.fromJson(jsonDataArray.get(1), BananaLeafDTO.class);

            System.out.println(bananaLeafDTO.getDecription());
            System.out.println(bananaLeafDTO.getRestaurant());

            List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
            restaurantDTOS.add(new RestaurantDTO(
                bananaLeafDTO.getRestaurant(),
                bananaLeafDTO.getDecription(),
                BananaLeafDTO.getMenuDTOsFromBananaLeafMenus(bananaLeafDTO.getMenus())
            ));

            return new RestaurantsDTO(restaurantDTOS);
        } catch (InterruptedException e) {
            throw new WebApplicationException("Unable to find the restaurants");
        }
    }

}