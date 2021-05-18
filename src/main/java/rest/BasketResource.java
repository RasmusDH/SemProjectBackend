/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dtos.basket.BasketDTO;
import dtos.basket.BasketItemDTO;
import dtos.basket.EditBasketDTO;
import entities.basket.BasketRepository;
import entities.basket.EditBasketType;
import facades.BasketFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import rest.provider.Provider;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author peter
 */
@Path("basket")
public class BasketResource extends Provider {


    Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BasketRepository REPO = BasketFacade.getInstance(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    public BasketResource() {
    }

    @Override
    public Response getById(int id) {
        BasketDTO basketDTO = REPO.getBasket(new Long(id));
        return Response.ok(GSON.toJson(basketDTO)).build();
    }

    @Override
    public Response getAll() {
        throw new UnsupportedOperationException(
            "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Path("/create")
    @GET
    public Response create() {
        BasketDTO basketDTO = REPO.create();
        return Response.ok(basketDTO).build();
    }

    @RolesAllowed({"user", "admin"})
    @Path("/add")
    @POST
    public Response addItem(String jsonBody) {
        String userName = securityContext.getUserPrincipal().getName();
        BasketItemDTO basketItemDTO = GSON.fromJson(jsonBody, BasketItemDTO.class);
        BasketDTO basketDTO = REPO.addToBasket(userName, basketItemDTO);

        return Response.ok(basketDTO).build();

    }

    @RolesAllowed({"user", "admin"})
    @GET
    @Path("/active")
    public Response getUsersActiveBasket() {
        String username = securityContext.getUserPrincipal().getName();
        BasketDTO basketDTO = REPO.getUsersActiveBasket(username);
        return Response.ok(GSON.toJson(basketDTO)).build();
    }

    @Override
    @RolesAllowed({"user", "admin"})
    public Response update(int id, String jsonBody) {
        EditBasketDTO editBasketDTO = GSON.fromJson(jsonBody, EditBasketDTO.class);
        BasketItemDTO basketItemDTO = REPO.editBasket(editBasketDTO.getType(), (long) id);
        return Response.ok(basketItemDTO).build();
    }

    @Override
    public Response delete(int id) {
        throw new UnsupportedOperationException(
            "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response create(String jsonBody) {
        throw new UnsupportedOperationException(
            "Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
