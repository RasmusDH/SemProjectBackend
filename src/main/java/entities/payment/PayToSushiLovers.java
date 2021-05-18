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

public class PayToSushiLovers implements Payment {

    private final PaymentFactoryDTO paymentFactoryDTO;

    public PayToSushiLovers(PaymentFactoryDTO paymentFactoryDTO) {
        this.paymentFactoryDTO = paymentFactoryDTO;
    }

    @Override
    public void pay() throws WebApplicationException {
        try {
            List<BasketItemDTO> sushiDishes = getSpecifiedBasketItemsByRestaurant("Sushi Lovers", paymentFactoryDTO);
            HttpURLConnection connection = getUrlConnection("https://api.tobias-z.com/sushi/api/order");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(new Gson().toJson(sushiDishes));
            writer.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new WebApplicationException(
                    "Could not pay for sushi at Sushi Lovers. Response code: " + responseCode, responseCode
                );
            }
            System.out.println("Response from sushi lovers: " + responseCode);
        } catch (IOException e) {
            throw new WebApplicationException("Could not pay for sushi at Sushi Lovers", 500);
        }
    }

}
