/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    
    @ManyToOne
    private User user;

    @OneToMany(
        mappedBy = "basket",
        cascade = CascadeType.PERSIST
    )
    private List<BasketItem> items;

    public Basket(User user) {
        this.items = new ArrayList<>();
        this.user = user;
    }

    public Basket() {       
    }
    
    public void addItems(BasketItem basketItem) {
     if (basketItem != null) {    
         this.items.add(basketItem);
        basketItem.setBasket(this);
     }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
