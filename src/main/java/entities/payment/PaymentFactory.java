/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.payment;

import dtos.Order.PaymentFactoryDTO;
import dtos.basket.BasketDTO;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public class PaymentFactory {
       
    public Payment getPayment(PaymentFactoryDTO paymentFactoryDTO, 
            PaymentMethod paymentMethod) {
        switch(paymentMethod) {
            case SUSHI_LOVERS: {
                return new PayToSushiLovers(paymentFactoryDTO);
            } 
            case BANANA_LEAF: {
                return new PayToBananaLeaf(paymentFactoryDTO);
            }
            case ALCOHOL: {
                return new PayToAlcohol(paymentFactoryDTO);
            }
            case PIZZA_2610: {
                return new PayToPizza2610(paymentFactoryDTO);
            }
            default: {
                throw new WebApplicationException("Unknown paymentmethod " 
                        + paymentMethod.name());
            }
        }        
    }
    
}
