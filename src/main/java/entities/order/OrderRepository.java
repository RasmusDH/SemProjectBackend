/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.order;

import dtos.basket.BasketDTO;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public interface OrderRepository {
    
    public void createOrder(BasketDTO basketDTO) throws WebApplicationException;
    
}
