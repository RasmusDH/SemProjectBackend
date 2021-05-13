/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.payment;

import dtos.basket.BasketDTO;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public class PayToBananaLeaf implements Payment {
    
    BasketDTO basketDTO;

    public PayToBananaLeaf(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }
    
    @Override
    public void pay() throws WebApplicationException {
        
        System.out.println("Now paying to Banana Leaf");
        
    }
    
    
    
    
    
}
