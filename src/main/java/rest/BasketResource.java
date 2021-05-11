/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dtos.BasketDTO;
import dtos.BasketItemDTO;
import entities.basket.BasketRepository;
import facades.BasketFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    private static final BasketRepository REPO =  BasketFacade.getInstance(EMF);
    
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    /**
     * Creates a new instance of BasketResource
     */
    public BasketResource() {
    }

    /**
     * Retrieves representation of an instance of rest.BasketResource
     * @return an instance of java.lang.String
     */
   

    @Override
    public Response getById(int id) {
        BasketDTO basketDTO = REPO.getBasket(new Long(id));
        return Response.ok(GSON.toJson(basketDTO)).build();
    }

    @Override
    public Response getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Path("/create")
    @GET
    public Response create() {
        BasketDTO basketDTO = REPO.create();
        return Response.ok(basketDTO).build();
    }
    
    @Path("/add/{userName}")
    @POST
    public Response addItem(@PathParam("userName") String userName, String jsonBody) {
               
      BasketItemDTO basketItemDTO = GSON.fromJson(jsonBody, BasketItemDTO.class);
      BasketDTO basketDTO = REPO.addToBasket(userName, basketItemDTO);
      
      System.out.println(basketDTO);
      
      return Response.ok(basketDTO).build();
        
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
    public Response create(String jsonBody) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
