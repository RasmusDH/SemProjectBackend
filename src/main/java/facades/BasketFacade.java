/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.BasketDTO;
import dtos.BasketItemDTO;
import entities.BasketRepository;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public class BasketFacade implements BasketRepository {

    @Override
    public BasketDTO create(BasketDTO basketDTO) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BasketDTO addToBasket(BasketItemDTO basketItemDTO) throws WebApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
