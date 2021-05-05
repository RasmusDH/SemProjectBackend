/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dtos.BasketItemDTO;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public interface BasketItemRepository {
    
    public BasketItemDTO create(BasketItemDTO basketItemDTO) throws WebApplicationException;
        
}
