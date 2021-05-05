
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Basket;
import entities.BasketItem;
import entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class BasketDTO {
    
     private User user;
     private List<BasketItem> items;

    public BasketDTO(User user, List<BasketItem> items) {
        this.user = user;
        this.items = items;
    }
    
    public BasketDTO(Basket basket) {
        this.user = basket.getUser();
    }
    
    public List<BasketDTO> getAllBasketDtoes(List<Basket> bsList) {
        List<BasketDTO> bsDTO = new ArrayList<>();
        bsList.forEach(bs -> bsDTO.add(new BasketDTO(bs)));
        return bsDTO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }
    
    
    
    
    
}
