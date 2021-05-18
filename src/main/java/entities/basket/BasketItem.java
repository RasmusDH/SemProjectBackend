/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.basket;

import dtos.basket.BasketItemDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author peter
 */
@Entity
public class BasketItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String restaurantName;
    private String itemName;
    private int dishNumber;
    private int amount;
    private double price;
    
    @ManyToOne(optional = true)
    private Basket basket;
    
   
    public BasketItem(String restaurantName, int dishNumber, String itemName, int amount, double price) {
        this.restaurantName = restaurantName;
        this.dishNumber = dishNumber;
        this.amount = amount;
        this.price = price;
        this.itemName = itemName;
    }

    public BasketItem(BasketItemDTO basketItemDTO) {
        this.id = basketItemDTO.getId();
        this.restaurantName = basketItemDTO.getRestaurantName();
        this.dishNumber = basketItemDTO.getDishNumber();
        this.amount = basketItemDTO.getAmount();
        this.price = basketItemDTO.getPrice();
        this.itemName = basketItemDTO.getItemName();
    }
    
    public BasketItem() {
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Basket getBasket() {
        return basket;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BasketItem{" + "id=" + id + ", restaurantName=" + restaurantName + ", itemName=" + itemName + ", dishNumber=" + dishNumber + ", amount=" + amount + ", price=" + price + ", basket=" + basket + '}';
    }
    
    

}
