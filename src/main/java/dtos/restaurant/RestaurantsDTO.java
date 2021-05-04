package dtos.restaurant;

import java.util.List;

public class RestaurantsDTO {

    private List<RestaurantDTO> restaurants;

    public RestaurantsDTO(List<RestaurantDTO> restaurants) {
        this.restaurants = restaurants;
    }

    public List<RestaurantDTO> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantDTO> restaurants) {
        this.restaurants = restaurants;
    }
}

