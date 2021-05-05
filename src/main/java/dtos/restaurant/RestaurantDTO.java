package dtos.restaurant;

import dtos.restaurant.alcohol.AlcoholDTO;
import dtos.restaurant.bananaleaf.BananaLeafDTO;
import dtos.restaurant.pizza2610.Pizza2610DTO;
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
    
       public RestaurantDTO(AlcoholDTO alcoholDTO) {
        this.name = alcoholDTO.getName();
        this.description = alcoholDTO.getDescription();
        this.menus = AlcoholDTO.getMenuDTOsFromAlcoholMenus(alcoholDTO.getCourses());
    }

       public RestaurantDTO(Pizza2610DTO pizza2610DTO) {
        this.name = pizza2610DTO.getName();
        this.description =pizza2610DTO.getDescription();
        this.menus = pizza2610DTO.getMenuDTOsFromPizza2610Menus(pizza2610DTO.getCourses());
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
