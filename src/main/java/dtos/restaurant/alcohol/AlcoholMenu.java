/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.restaurant.alcohol;

/**
 *
 * @author peter
 */
public class AlcoholMenu {
    
    private Integer id;
    private String name;
    private double price;
    private String type;
    private String description;

    public AlcoholMenu(Integer id, String name, double price, String type, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}





/*
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

*/