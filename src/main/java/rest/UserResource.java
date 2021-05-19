/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.user.UserDTO;
import entities.UserRepository;
import facades.UserFacade;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rest.provider.Provider;

/**
 * REST Web Service
 *
 * @author peter
 */
@Path("user")
public class UserResource extends Provider {
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();  
    private static final UserRepository REPO =  UserFacade.getUserFacade(EMF);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    
    @GET
    @Path("bonuspoints/{userName}")
    public Response getBonusPoints(@PathParam("userName") String userName){
        double points = REPO.getBonusPoints(userName);
        String msg = "{\"bonusPoints\": " + points + "}";
        return Response.ok(msg).build();
    }
   
    @Override
    public Response getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response create(String user) {
        UserDTO u = GSON.fromJson(user, UserDTO.class);
        UserDTO newUser = REPO.create(u);
        return Response.ok(GSON.toJson(newUser)).build();
    }

    @Override
    public Response update(int id, String jsonBody) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
