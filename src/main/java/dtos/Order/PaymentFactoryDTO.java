/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.Order;

import dtos.basket.BasketDTO;

/**
 *
 * @author peter
 */
public class PaymentFactoryDTO {
    
    PaymentDTO paymentDTO;
    BasketDTO basketDTO;

    public PaymentFactoryDTO(PaymentDTO paymentDTO, BasketDTO basketDTO) {
        this.paymentDTO = paymentDTO;
        this.basketDTO = basketDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }

    public BasketDTO getBasketDTO() {
        return basketDTO;
    }

    public void setBasketDTO(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }
    
    
    
}
