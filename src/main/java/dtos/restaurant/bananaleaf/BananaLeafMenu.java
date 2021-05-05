package dtos.restaurant.bananaleaf;

import dtos.restaurant.MenuDTO;
import dtos.restaurant.sushilovers.SushiLoversMenu;
import java.util.List;
import java.util.stream.Collectors;

public class BananaLeafMenu {

    private Integer id;
    private String itemName;
    private double price;
    private String category;
    private String description;

    public BananaLeafMenu(Integer id, String itemName, double price, String category,
        String description) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
