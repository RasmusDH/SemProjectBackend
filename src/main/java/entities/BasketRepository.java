/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.BasketDTO;
import dtos.BasketItemDTO;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public interface BasketRepository {
    
    public BasketDTO create() throws WebApplicationException;
    public BasketDTO addToBasket(Long id, BasketItemDTO basketItemDTO) throws WebApplicationException;
    public BasketDTO getBasket(Long id) throws WebApplicationException;
    
}
