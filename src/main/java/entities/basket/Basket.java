/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.basket;

import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import entities.User;
import entities.order.OrderEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;

/**
 *
 * @author peter
 */
@Entity
public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean active;
    
    @ManyToOne
    private User user;

    @OneToMany(
        mappedBy = "basket",
        cascade = CascadeType.PERSIST
    )
    private List<BasketItem> items;
    
    @OneToOne(mappedBy = "basket")
    @JoinColumn(nullable = true)
    private OrderEntity order;
    
    
    public Basket(User user) {       
        this.items = new ArrayList<>();
        this.active = true;
        this.user = user;
    }
    
    public Basket() {
        this.items = new ArrayList<>();
        this.active = true;
    }
    
    public static List<BasketItem> getAllasketItem(List<BasketItemDTO> bItems) {
        List<BasketItem> items = new ArrayList<>();
        bItems.forEach(item -> items.add(new BasketItem(item)));
        return items;
    }
    
    public Basket(BasketDTO basketDTO) {
        this.items = getAllasketItem(basketDTO.getItems());
        this.active = true;
        this.id = basketDTO.getId();        
    }

    public void addItems(BasketItem basketItem) {
     if (basketItem != null) {    
         this.items.add(basketItem);
         basketItem.setBasket(this);
     }
    }
    
    public List<BasketItem> getItems() {
        return items;
    }
        
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Basket{" + "id=" + id + ", active=" + active + ", user=" + user + ", items=" + items + ", order=" + order + '}';
    }
    
    
    

    

}
