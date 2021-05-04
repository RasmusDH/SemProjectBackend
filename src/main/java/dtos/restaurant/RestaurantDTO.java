package dtos.restaurant;

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
