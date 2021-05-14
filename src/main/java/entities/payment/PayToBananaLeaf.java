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
public class PayToBananaLeaf implements Payment {

    private PaymentFactoryDTO paymentFactoryDTO;

    public PayToBananaLeaf(PaymentFactoryDTO paymentFactoryDTO) {
        this.paymentFactoryDTO = paymentFactoryDTO;
    }

    private List<BasketItemDTO> getBananaLeafBasketItems() {
        List<BasketItemDTO> bananaLeafDishes = new ArrayList<>();
        for (BasketItemDTO basketItemDTO : paymentFactoryDTO.getBasketDTO().getItems()) {
            if (basketItemDTO.getRestaurantName() == "Banana leaf") {
                bananaLeafDishes.add(basketItemDTO);
            }
        }
        return bananaLeafDishes;
    }

    @Override
    public void pay() throws WebApplicationException {
        List<BasketItemDTO> bananaLeafDishes = getBananaLeafBasketItems();
        System.out.println("Now paying to Banana Leaf");
    }

}
