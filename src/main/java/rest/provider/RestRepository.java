package rest.provider;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface RestRepository {

    @Path("/{id}")
    @GET
    Response getById(@PathParam("id") int id);

    @GET
    Response getAll();

    @POST
    Response create(String jsonBody);

    @PUT
    @Path("/{id}")
    Response update(@PathParam("id") int id, String jsonBody);

    @DELETE
    @Path("/{id}")
    Response delete(@PathParam("id") int id);
}
