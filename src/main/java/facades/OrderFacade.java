/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import dtos.Order.OrderDTO;
import dtos.Order.PaymentDTO;
import dtos.Order.PaymentFactoryDTO;
import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import entities.User;
import entities.basket.Basket;
import entities.basket.BasketItem;
import entities.order.OrderEntity;
import entities.order.OrderRepository;
import entities.payment.Payment;
import entities.payment.PaymentFactory;
import entities.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
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

    private BasketDTO getBasketDTOFromUserName(String userName) {
        EntityManager em = emf.createEntityManager();

        try {
            Basket basket = (Basket) em
                    .createQuery(
                            "SELECT b FROM Basket b WHERE b.user.userName = :userName AND b.active = true")
                    .setParameter("userName", userName)
                    .getSingleResult();

            return new BasketDTO(basket);

        } catch (Exception e) {
            throw new WebApplicationException("Basket not found");
        }

    }

    private double calculateRemaingBonusPoints(double existingBonusPoints, double totalPrice) {
        double restBonusPoints = existingBonusPoints - totalPrice;

        if (restBonusPoints < 0) {
            restBonusPoints = 0;
        }
        return restBonusPoints;
    }

    private void makePaymentForProducts(PaymentFactoryDTO paymentFactoryDTO) {
        PaymentFactory paymentFactory = new PaymentFactory();

        paymentFactoryDTO.getBasketDTO().getItems().sort((item1, item2)
                -> item1.getRestaurantName().compareTo(item2.getRestaurantName()));

        String oldRestaurant = "";
        for (BasketItemDTO item : paymentFactoryDTO.getBasketDTO().getItems()) {
            if (!oldRestaurant.equals(item.getRestaurantName())) {
                oldRestaurant = item.getRestaurantName();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        PaymentMethod paymentMethod = getPaymentMethod(item.getRestaurantName());
                        Payment payment = paymentFactory.getPayment(paymentFactoryDTO, paymentMethod);
                        payment.pay();
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        }

    }

    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private double giveBonusPointsToUser(BasketDTO basketDTO, String userName, boolean isUsingBonusPoints) {
        double pointsPercent = 0.05;
        double totalPrice = 0.0;
        for (BasketItemDTO basketItem : basketDTO.getItems()) {
            totalPrice = totalPrice + (basketItem.getAmount() * basketItem.getPrice());
        }
        double bonusPoints = (totalPrice * pointsPercent);

        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, userName);
            double currentPoints = user.getBonusPoints();
            em.getTransaction().begin();

            if (isUsingBonusPoints) {
                currentPoints = calculateRemaingBonusPoints(currentPoints, totalPrice);
            }
            user.setBonusPoints(round(currentPoints + bonusPoints, 2));
            em.getTransaction().commit();

        } catch (Exception e) {
            throw new WebApplicationException("Bonuspoint calculation mistake");

        } finally {
            em.close();
        }
        return bonusPoints;

    }

    @Override
    public OrderDTO createOrder(PaymentDTO paymentDTO) throws WebApplicationException {
        BasketDTO basketDTO = getBasketDTOFromUserName(paymentDTO.getUserName());
        PaymentFactoryDTO paymentFactoryDTO = new PaymentFactoryDTO(paymentDTO, basketDTO);
        makePaymentForProducts(paymentFactoryDTO);

        double bonusPoints = giveBonusPointsToUser(basketDTO, paymentDTO.getUserName(), paymentDTO.isIsUsingBonusPoints());

        EntityManager em = emf.createEntityManager();

        Basket basket = em.find(Basket.class, basketDTO.getId());

        OrderEntity order = new OrderEntity(paymentDTO.getContactInfo().getName(),
                paymentDTO.getContactInfo().getEmail(),
                paymentDTO.getContactInfo().getPhone(),
                paymentDTO.getContactInfo().getAddress(),
                paymentDTO.getContactInfo().getDelivery(),
                bonusPoints,
                basket);

        try {
            em.getTransaction().begin();
            basket.setActive(false);
            em.persist(order);
            em.getTransaction().commit();
            return new OrderDTO(order);
        } finally {
            em.close();
        }
    }

    @Override
    public List<OrderDTO> getAllOrders(String userName) throws WebApplicationException {

        EntityManager em = emf.createEntityManager();

        List<OrderEntity> orders;

        try {
            orders = em.createQuery(
                    "SELECT o FROM OrderEntity o JOIN o.basket b WHERE b.user.userName = :userName", OrderEntity.class
            )
                    .setParameter("userName", userName)
                    .getResultList();

        } catch (Exception e) {
            throw new WebApplicationException("No orders available");
        }

        return OrderDTO.getAllOrderDtoes(orders);
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

    @Override
    public OrderDTO getOrderById(Long id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            OrderEntity orderEntity = em.find(OrderEntity.class, id);
            if (orderEntity == null) {
                throw new WebApplicationException("Could not find an order with id: " + id, 404);
            }
            return new OrderDTO(orderEntity);
        } finally {
            em.close();
        }
    }
}
