/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.payment;

import com.google.gson.Gson;
import dtos.Order.PaymentFactoryDTO;
import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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

    @Override
    public void pay() throws WebApplicationException {
        List<BasketItemDTO> bananaLeafDishes = getSpecifiedBasketItemsByRestaurant("Banana leaf", paymentFactoryDTO);
        
        try {
        HttpURLConnection connection = getUrlConnection("https://peterrambeckandersen.com/tomcat/Bananaleaf/api/restaurant/order");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(new Gson().toJson(bananaLeafDishes));
            writer.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new WebApplicationException(
                    "Could not pay for the order at banana leaf. Response code: " + responseCode, responseCode
                );
            }
            System.out.println("Response from banana leaf: " + responseCode);
        } catch (IOException e) {
            throw new WebApplicationException("Could not pay for banana leaf order at banana leaf", 500);
        }
        
        System.out.println("Now paying to Banana Leaf");
    }

}
