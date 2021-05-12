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
public class PaymentFactory {
       
    public Payment getPayment(BasketDTO basketDTO, 
            PaymentMethod paymentMethod) {
        switch(paymentMethod) {
            case SUSHI_LOVERS: {
                return new PayToSushiLovers(basketDTO);
            } 
            case BANANA_LEAF: {
                return new PayToBananaLeaf(basketDTO);
            }
            default: {
                throw new WebApplicationException("Unknown paymentmethod " 
                        + paymentMethod.name());
            }
        }        
    }
    
}
