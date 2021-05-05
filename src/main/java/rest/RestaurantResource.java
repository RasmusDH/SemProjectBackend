package rest;

import dtos.restaurant.RestaurantsDTO;
import facades.RestaurantFacade;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import rest.provider.Provider;

@Path("restaurants")
public class RestaurantResource extends Provider {

    private final RestaurantFacade facade = RestaurantFacade.getInstance();

    @Override
    public Response getAll() {
        RestaurantsDTO restaurantsDTO = facade.getAllRestaurants();
        return Response.ok(GSON.toJson(restaurantsDTO)).build();
    }

    @Override
    public Response getById(int id) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Response create(String jsonBody) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Response update(int id, String jsonBody) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Response delete(int id) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}