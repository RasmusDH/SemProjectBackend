/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.order;

import dtos.Order.OrderDTO;
import dtos.Order.PaymentDTO;
import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import entities.User;
import entities.basket.BasketItem;
import java.util.List;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public interface OrderRepository {
    
    public OrderDTO createOrder(PaymentDTO paymentDTO) throws WebApplicationException;

    OrderDTO getOrderById(Long id) throws WebApplicationException;
  
    public List<OrderDTO> getAllOrders(String userName) throws WebApplicationException;
    
}
