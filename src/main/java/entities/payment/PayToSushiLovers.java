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
public class PayToSushiLovers implements Payment{
    
    BasketDTO basketDTO;

    public PayToSushiLovers(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }
    
    
    @Override
    public void pay() throws WebApplicationException {
        System.out.println("Now paying to Sushi Lovers");
    }
    
}
