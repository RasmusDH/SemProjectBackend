package dtos.restaurant.bananaleaf;

import dtos.restaurant.MenuDTO;
import java.util.List;
import java.util.stream.Collectors;

public class BananaLeafDTO {

    private String restaurant;
    private String decription;
    private List<BananaLeafMenu> menus;

    public BananaLeafDTO(String name, String description,
        List<BananaLeafMenu> bananaLeafMenus) {
        this.restaurant = name;
        this.decription = description;
        this.menus = bananaLeafMenus;
    }

    // We do this so that we can put it on the RestaurantDTO
    public static List<MenuDTO> getMenuDTOsFromBananaLeafMenus(List<BananaLeafMenu> menus) {
        return menus.stream()
            .map(banana -> {
                MenuDTO menuDTO = new MenuDTO(banana.getId(),
                    banana.getItemName(),
                    banana.getPrice(),
                    banana.getCategory(),
                    banana.getDescription());
                return menuDTO;
            }).collect(Collectors.toList());
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public List<BananaLeafMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<BananaLeafMenu> menus) {
        this.menus = menus;
    }
}
