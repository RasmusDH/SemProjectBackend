package rest.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class Provider implements RestRepository {

    public static final EntityManagerFactory EMF;
    public static final Gson GSON;

    static {
        EMF = EMF_Creator.createEntityManagerFactory();
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }

}
