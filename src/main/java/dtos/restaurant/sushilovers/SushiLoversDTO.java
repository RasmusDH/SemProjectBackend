package dtos.restaurant.sushilovers;

import dtos.restaurant.MenuDTO;
import java.util.List;
import java.util.stream.Collectors;

public class SushiLoversDTO {

    private String name;
    private String description;
    private List<SushiLoversMenu> meals;

    public SushiLoversDTO(String name, String description,
        List<SushiLoversMenu> meals) {
        this.name = name;
        this.description = description;
        this.meals = meals;
    }

    // We do this so that we can put it on the RestaurantDTO
    public static List<MenuDTO> getMenuDTOsFromSushiLoversMenus(List<SushiLoversMenu> menus) {
        return menus.stream()
            .map(sushi -> {
                MenuDTO menuDTO = new MenuDTO(sushi.getId(),
                    sushi.getName(),
                    sushi.getPrice(),
                    sushi.getCategory(),
                    sushi.getDescription());
                return menuDTO;
            }).collect(Collectors.toList());
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

    public List<SushiLoversMenu> getMeals() {
        return meals;
    }

    public void setMeals(List<SushiLoversMenu> meals) {
        this.meals = meals;
    }
}
