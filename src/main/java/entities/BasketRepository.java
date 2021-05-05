/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public interface BasketRepository {
    
 
    public BasketDTO create(BasketDTO basketDTO) throws WebApplicationException;
    public BasketDTO addToBasket(BasketItemDTO basketItemDTO) throws WebApplicationException;
  
   
}
