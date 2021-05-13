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
public class PayToSushiLovers implements Payment{
    
    private PaymentFactoryDTO paymentFactoryDTO;

    public PayToSushiLovers(PaymentFactoryDTO paymentFactoryDTO) {
        this.paymentFactoryDTO = paymentFactoryDTO;
    }
    
    private List<BasketItemDTO> getSushiBasketItems() {
        List<BasketItemDTO> sushiDishes = new ArrayList<>();
        for (BasketItemDTO basketItemDTO : paymentFactoryDTO.getBasketDTO().getItems()) {
            if (basketItemDTO.getRestaurantName() == "Sushi Lovers") {
                sushiDishes.add(basketItemDTO);
            }
        }
        return sushiDishes;
    }
        
    @Override
    public void pay() throws WebApplicationException {
        
        List<BasketItemDTO> sushiDishes = getSushiBasketItems();        
        System.out.println("Now paying to Sushi Lovers");
    }
    
}
