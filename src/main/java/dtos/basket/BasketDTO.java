
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.basket;

import dtos.user.UserDTO;
import entities.basket.Basket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class BasketDTO {
    
    
    private Long id;
    private boolean active;
    private double totalPrice;
       
    private UserDTO user;
    private List<BasketItemDTO> items;

    public BasketDTO(Long id, UserDTO user, List<BasketItemDTO> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }
     
    public BasketDTO(Basket basket) {
        this.id = basket.getId();
        this.items = BasketItemDTO.getAllasketItemDtoes(basket.getItems());
        this.user = new UserDTO(basket.getUser());
        this.totalPrice = basket.getTotalPrice();
    }
    
    public static List<BasketDTO> getAllBasketDtoes(List<Basket> bsList) {
        List<BasketDTO> bsDTO = new ArrayList<>();
        bsList.forEach(bs -> bsDTO.add(
            new BasketDTO(
                bs.getId(),
                new UserDTO(bs.getUser()),
                BasketItemDTO.getAllasketItemDtoes(bs.getItems())
            )
        ));
        return bsDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
       
    public List<BasketItemDTO> getItems() {
        return items;
    }

    public void setItems(List<BasketItemDTO> items) {
        this.items = items;
    }
    
    
    
    
    
}
