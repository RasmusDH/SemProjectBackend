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
public class PayToPizza2610 implements Payment {

    private PaymentFactoryDTO paymentFactoryDTO;

    public PayToPizza2610(PaymentFactoryDTO paymentFactoryDTO) {
        this.paymentFactoryDTO = paymentFactoryDTO;
    }

    @Override
    public void pay() throws WebApplicationException {
        try {
            List<BasketItemDTO> pizzaDishes = getSpecifiedBasketItemsByRestaurant("Pizza 2610", paymentFactoryDTO);
            HttpURLConnection connection = getUrlConnection("https://osvaldo.dk/tomcat/Pizza2610/api/pay/");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(new Gson().toJson(pizzaDishes));
            writer.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new WebApplicationException(
                    "Could not pay for the order at Pizza 2610. Response code: " + responseCode, responseCode
                );
            }
            System.out.println("Response from Pizza 2610: " + responseCode);
        } catch (IOException e) {
            throw new WebApplicationException("Could not pay for the order at Pizza 2610", 500);
        }
    }

}
