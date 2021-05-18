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
public class PayToAlcohol implements Payment {
    
   private PaymentFactoryDTO paymentFactoryDTO;

    public PayToAlcohol(PaymentFactoryDTO paymentFactoryDTO) {
        this.paymentFactoryDTO = paymentFactoryDTO;
    }
    
         
    @Override
    public void pay() throws WebApplicationException {
         try {
            List<BasketItemDTO> sushiDishes = getSpecifiedBasketItemsByRestaurant("Night shop", paymentFactoryDTO);
            HttpURLConnection connection = getUrlConnection("https://ditlevsoftware.com/tomcat/alcohol-shop/api/payment/");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(new Gson().toJson(sushiDishes));
            writer.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new WebApplicationException(
                    "Could not pay for the order at Noght SHop. Response code: " + responseCode, responseCode
                );
            }
            System.out.println("Response from Night SHop: " + responseCode);
        } catch (IOException e) {
            throw new WebApplicationException("Could not pay for the order at Noght SHop", 500);
        }
    }
    
}
