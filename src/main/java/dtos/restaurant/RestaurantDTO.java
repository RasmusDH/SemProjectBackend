package dtos.restaurant;

import dtos.restaurant.bananaleaf.BananaLeafDTO;
import dtos.restaurant.sushilovers.SushiLoversDTO;
import java.util.List;

public class RestaurantDTO {

    private String name;
    private String description;
    private List<MenuDTO> menus;

    public RestaurantDTO(String name, String description, List<MenuDTO> menus) {
        this.name = name;
        this.description = description;
        this.menus = menus;
    }

    public RestaurantDTO(SushiLoversDTO sushiLoversDTO) {
        this.name = sushiLoversDTO.getName();
        this.description = sushiLoversDTO.getDescription();
        this.menus = SushiLoversDTO.getMenuDTOsFromSushiLoversMenus(sushiLoversDTO.getMeals());
    }

    public RestaurantDTO(BananaLeafDTO bananaLeafDTO) {
        this.name = bananaLeafDTO.getRestaurant();
        this.description = bananaLeafDTO.getDecription();
        this.menus = BananaLeafDTO.getMenuDTOsFromBananaLeafMenus(bananaLeafDTO.getMenus());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }
}
