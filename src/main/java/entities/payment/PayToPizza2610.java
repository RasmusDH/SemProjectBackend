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
public class PayToPizza2610 implements Payment {

    private PaymentFactoryDTO paymentFactoryDTO;

    public PayToPizza2610(PaymentFactoryDTO paymentFactoryDTO) {
        this.paymentFactoryDTO = paymentFactoryDTO;
    }

    private List<BasketItemDTO> getPizza2610BasketItemsDTO() {
        List<BasketItemDTO> pizzaDishes = new ArrayList<>();
        for (BasketItemDTO basketItemDTO : paymentFactoryDTO.getBasketDTO().getItems()) {
            if (basketItemDTO.getRestaurantName() == "Pizza 2610") {
                pizzaDishes.add(basketItemDTO);
            }
        }
        return pizzaDishes;

    }

    @Override
    public void pay() throws WebApplicationException {
        List<BasketItemDTO> pizzaDishes = getPizza2610BasketItemsDTO();
        System.out.println("Pizza pay");
    }

}
