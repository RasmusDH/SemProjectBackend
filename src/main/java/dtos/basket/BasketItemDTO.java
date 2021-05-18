/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.basket;

import entities.basket.BasketItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class BasketItemDTO {
    
    private Long id;
    private String restaurantName;
    private String itemName;
    private int dishNumber;
    private int amount;
    private double price;

    public BasketItemDTO(String restaurantName, int dishNumber, String itemName, int amount, double price) {
        this.restaurantName = restaurantName;
        this.dishNumber = dishNumber;
        this.amount = amount;
        this.price = price;
        this.itemName = itemName;
    }

    public BasketItemDTO(BasketItem basketItem) {
        this.id = basketItem.getId();
        this.restaurantName = basketItem.getRestaurantName();
        this.dishNumber = basketItem.getDishNumber();
        this.amount = basketItem.getAmount();
        this.price = basketItem.getPrice();
        this.itemName = basketItem.getItemName();
    }
    
    public static List<BasketItemDTO> getAllasketItemDtoes(List<BasketItem> bItems) {
        List<BasketItemDTO> bItemsDTO = new ArrayList<>();
        bItems.forEach(item -> bItemsDTO.add(new BasketItemDTO(item)));
        return bItemsDTO;
    }
    
    

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getDishNumber() {
        return dishNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    
    
    public void setDishNumber(int dishNumber) {
        this.dishNumber = dishNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BasketItemDTO{" + "id=" + id + ", restaurantName=" + restaurantName + ", itemName=" + itemName + ", dishNumber=" + dishNumber + ", amount=" + amount + ", price=" + price + '}';
    }
    
    
  
}
