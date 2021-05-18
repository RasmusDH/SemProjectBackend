/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.payment;

import dtos.Order.PaymentFactoryDTO;
import dtos.basket.BasketItemDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.WebApplicationException;

/**
 * @author peter
 */
public interface Payment {

    public void pay() throws WebApplicationException;

    default HttpURLConnection getUrlConnection(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", "server");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        return connection;
    }

    default List<BasketItemDTO> getSpecifiedBasketItemsByRestaurant(String restaurantName, PaymentFactoryDTO paymentFactoryDTO) {
        List<BasketItemDTO> dishes = new ArrayList<>();
        for (BasketItemDTO basketItemDTO : paymentFactoryDTO.getBasketDTO().getItems()) {
            if (basketItemDTO.getRestaurantName().equals(restaurantName)) {
                dishes.add(basketItemDTO);
            }
        }
        return dishes;
    }

}
