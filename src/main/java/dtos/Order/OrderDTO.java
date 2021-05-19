/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.Order;

import dtos.basket.BasketDTO;
import entities.order.OrderEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class OrderDTO {
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String delivery;
    private double generatedBonusPoints; 
       
    BasketDTO basketDTO;

    public OrderDTO(Long id, String name, String email, String phone, String address, String delivery, BasketDTO basketDTO) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.delivery = delivery;
        this.basketDTO = basketDTO;
    }
    
    public OrderDTO(OrderEntity order) {
        this.id = order.getId();
        this.name = order.getName();
        this.email = order.getEmail();
        this.phone = order.getPhone();
        this.address = order.getAddress();
        this.delivery = order.getDelivery();
        this.basketDTO = new BasketDTO(order.getBasket());
        this.generatedBonusPoints = order.getGeneratedBonusPoints();
    }
    
    public static List<OrderDTO> getAllOrderDtoes(List<OrderEntity> orders) {
        List<OrderDTO> ordersDTO = new ArrayList<>();
        orders.forEach(item -> ordersDTO.add(new OrderDTO(item)));
        return ordersDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public BasketDTO getBasketDTO() {
        return basketDTO;
    }

    public void setBasketDTO(BasketDTO basketDTO) {
        this.basketDTO = basketDTO;
    }

    public double getGeneratedBonusPoints() {
        return generatedBonusPoints;
    }

    public void setGeneratedBonusPoints(double generatedBonusPoints) {
        this.generatedBonusPoints = generatedBonusPoints;
    }
    
    
    
    
}
