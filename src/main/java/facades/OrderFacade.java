/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import entities.User;
import entities.basket.Basket;
import entities.basket.BasketItem;
import entities.order.OrderRepository;
import entities.payment.Payment;
import entities.payment.PaymentFactory;
import entities.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import utils.EMF_Creator;

/**
 *
 * @author peter
 */
public class OrderFacade implements OrderRepository {

    private static OrderFacade instance;
    private static EntityManagerFactory emf;

    public static OrderFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OrderFacade();
        }
        return instance;
    }

    @Override
    public void createOrder(BasketDTO basketDTO) throws WebApplicationException {
        PaymentFactory paymentFactory = new PaymentFactory();

        basketDTO.getItems().sort((item1, item2)
                -> item1.getRestaurantName().compareTo(item2.getRestaurantName()));

        String oldRestaurant = "";
        for (BasketItemDTO item : basketDTO.getItems()) {
            if (!oldRestaurant.equals(item.getRestaurantName())) {
                oldRestaurant = item.getRestaurantName();
                
                PaymentMethod paymentMethod = getPaymentMethod(item.getRestaurantName());
                Payment payment = paymentFactory.getPayment(basketDTO, paymentMethod);
                
                payment.pay();
            }
        }
    }

    private PaymentMethod getPaymentMethod(String restaurantName) {
        switch (restaurantName) {
            case "Sushi Lovers": {
                return PaymentMethod.SUSHI_LOVERS;
            }
            case "Banana leaf": {
                return PaymentMethod.BANANA_LEAF;
            }
            case "Night shop": {
                return PaymentMethod.ALCOHOL;
            }
            case "Pizza 2610": {
                return PaymentMethod.PIZZA_2610;
            }
            default: {
                throw new WebApplicationException("Restaurant not found");
            }
        }
    }
    
    public static void main(String[] args) {
        Basket basket = new Basket(new User("Hanse", "4"));
        basket.addItems(new BasketItem("Sushi Lovers", 10, 2, 10.02));
        basket.addItems(new BasketItem("Sushi Lovers", 1, 20, 111.0));
        basket.addItems(new BasketItem("Sushi Lovers", 12, 2, 10.02));
        
        basket.addItems(new BasketItem("Banana leaf", 10, 2, 10.02));
        basket.addItems(new BasketItem("Banana leaf", 10, 2, 10.02));
        basket.addItems(new BasketItem("Banana leaf", 10, 2, 10.02));
                
        BasketDTO basketDTO = new BasketDTO(basket);
        OrderFacade facade = OrderFacade.getInstance(EMF_Creator.createEntityManagerFactory());
        facade.createOrder(basketDTO);
    }

}
