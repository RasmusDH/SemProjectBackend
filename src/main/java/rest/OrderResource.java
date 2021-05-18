/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import dtos.Order.OrderDTO;
import dtos.Order.PaymentDTO;
import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import entities.User;
import entities.basket.BasketItem;
import entities.order.OrderRepository;
import facades.OrderFacade;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import rest.provider.Provider;

/**
 * REST Web Service
 *
 * @author peter
 */
@Path("order")
public class OrderResource extends Provider {

    private static final OrderRepository REPO = OrderFacade.getInstance(EMF);

    @Context
    SecurityContext securityContext;

    @Context
    private UriInfo context;

    public OrderResource() {
    }

    @Override
    public Response getById(int id) {
        OrderDTO orderDTO = REPO.getOrderById((long) id);
        return Response.ok(orderDTO).build();
    }

    @GET
    @Path("orders")  
    @RolesAllowed("user")
    public Response getAllOrders() {
        String userName = securityContext.getUserPrincipal().getName();
        List<OrderDTO> orderDTO = REPO.getAllOrders(userName);
        return Response.ok(GSON.toJson(orderDTO)).build(); // 
    }

    @Override
    @RolesAllowed({"user", "admin"})
    public Response create(String jsonBody) {
        String userName = securityContext.getUserPrincipal().getName();
        PaymentDTO paymentDTO = GSON.fromJson(jsonBody, PaymentDTO.class);
        paymentDTO.setUserName(userName);
        OrderDTO orderDTO = REPO.createOrder(paymentDTO);
        return Response.ok(GSON.toJson(orderDTO)).build();
    }

    @Override
    public Response update(int id, String jsonBody) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
