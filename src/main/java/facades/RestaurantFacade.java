package facades;

import com.google.gson.Gson;
import dtos.restaurant.RestaurantDTO;
import dtos.restaurant.RestaurantsDTO;
import dtos.restaurant.alcohol.AlcoholDTO;
import dtos.restaurant.bananaleaf.BananaLeafDTO;
import dtos.restaurant.pizza2610.Pizza2610DTO;
import dtos.restaurant.sushilovers.SushiLoversDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import utils.HttpUtil;

public class RestaurantFacade {

    private static RestaurantFacade instance;

    private RestaurantFacade() {
    }

    public static RestaurantFacade getInstance() {
        if (instance == null) {
            instance = new RestaurantFacade();
        }
        return instance;
    }

    private String[] getRestaurantURLS() {
        // IMPORTANT!!!! DONT MOVE THEM AROUND!!!!
        return new String[]{
            Endpoint.SUSHI_LOVERS.getUrl(),
            Endpoint.BANANA_LEAF.getUrl(),
            Endpoint.ALCOHOL.getUrl(),
            Endpoint.PIZZA2610.getUrl()
        };
    }

    private List<RestaurantDTO> mapJsonDataToRestaurants(List<String> jsonDataArray) {
        Gson gson = new Gson();

        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        restaurantDTOS.add(new RestaurantDTO(gson.fromJson(jsonDataArray.get(0), SushiLoversDTO.class)));
        restaurantDTOS.add(new RestaurantDTO(gson.fromJson(jsonDataArray.get(1), BananaLeafDTO.class)));
        restaurantDTOS.add(new RestaurantDTO(gson.fromJson(jsonDataArray.get(2), AlcoholDTO.class)));
        restaurantDTOS.add(new RestaurantDTO(gson.fromJson(jsonDataArray.get(3), Pizza2610DTO.class)));
        return restaurantDTOS;
    }

    public RestaurantsDTO getAllRestaurants() throws WebApplicationException {
        try {

            String[] urls = getRestaurantURLS();
            List<String> jsonDataArray = HttpUtil.fetchMany(urls);

            List<RestaurantDTO> restaurantDTOS = mapJsonDataToRestaurants(jsonDataArray);

            return new RestaurantsDTO(restaurantDTOS);
        } catch (InterruptedException e) {
            throw new WebApplicationException("Unable to find the restaurants");
        }
    }

}

enum Endpoint {
    SUSHI_LOVERS("https://api.tobias-z.com/sushi/api/meals/"),
    BANANA_LEAF("https://peterrambeckandersen.com/tomcat/Bananaleaf/api/restaurant/"),
    ALCOHOL("https://ditlevsoftware.com/tomcat/alcohol-shop/api/menu/"),
    PIZZA2610("https://osvaldo.dk/tomcat/Pizza2610/api/menu/");

    private final String url;

    Endpoint(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}