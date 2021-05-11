/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.basket;

import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import javax.ws.rs.WebApplicationException;

/**
 * @author peter
 */
public interface BasketRepository {

    public BasketDTO create() throws WebApplicationException;

    public BasketDTO addToBasket(String userName, BasketItemDTO basketItemDTO) throws WebApplicationException;

    public BasketDTO getBasket(Long id) throws WebApplicationException;

    BasketDTO getUsersActiveBasket(String userName) throws WebApplicationException;

    BasketItemDTO editBasket(EditBasketType type, Long itemId) throws WebApplicationException;

}
