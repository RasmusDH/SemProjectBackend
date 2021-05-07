/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.BasketDTO;
import dtos.BasketItemDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    private int dummy;
    
    // @ManyToOne
    // private User user;

    @OneToMany(
        mappedBy = "basket",
        cascade = CascadeType.PERSIST
    )
    
    private List<BasketItem> items;
    

//    public Basket() {
//        this.items = new ArrayList<>();
//        // this.user = user;
//    }

    public Basket() {       
        this.items = new ArrayList<>();
        this.dummy = 1;
    }
    
    public Basket(BasketDTO basketDTO) {
       this.id = basketDTO.getId();
       this.items = new ArrayList<>();
       for (BasketItemDTO bdtos: basketDTO.getItems()) {
           items.add(new BasketItem(bdtos));
       }
       this.dummy = 1;
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

    public int getDummy() {
        return dummy;
    }

    public void setDummy(int dummy) {
        this.dummy = dummy;
    }
    
    

}