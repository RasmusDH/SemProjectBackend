/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.payment;

import dtos.Order.PaymentFactoryDTO;
import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public class PayToAlcohol implements Payment {
    
   private PaymentFactoryDTO paymentFactoryDTO;

    public PayToAlcohol(PaymentFactoryDTO paymentFactoryDTO) {
        this.paymentFactoryDTO = paymentFactoryDTO;
    }
    
    private List<BasketItemDTO> getAlcoholBasketItems() {
        List<BasketItemDTO> alcoholDishes = new ArrayList<>();
        for (BasketItemDTO basketItemDTO : paymentFactoryDTO.getBasketDTO().getItems()) {
            if (basketItemDTO.getRestaurantName() == "Night shop") {
                alcoholDishes.add(basketItemDTO);
            }
        }
        return alcoholDishes;
    }
      
    @Override
    public void pay() throws WebApplicationException {
        List<BasketItemDTO> alcoholDishes = getAlcoholBasketItems();
        System.out.println("Pay alcohol");
    }
    
}
