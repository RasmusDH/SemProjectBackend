package rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dtos.Example.AnimeDTO;
import dtos.Example.BeerDTO;
import dtos.Example.CatDTO;
import dtos.Example.ChuckDTO;
import dtos.Example.CombinedDTO;
import dtos.Example.JokeDTO;
import dtos.Example.WeatherDTO;
import dtos.RenameMeDTO;
import entities.User;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;
import utils.HttpUtil;

/**
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource {

    Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("select u from User u", entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fetchdata")
    public String getFetchData() {
        RenameMeDTO dto = null;
        try {
            String JsonResponse = HttpUtil.fetchData("www.google.com");
            dto = gson.fromJson(JsonResponse, RenameMeDTO.class);
        } catch (Exception e) {
        }
        return gson.toJson(dto);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("fetchMany")
    public String getFetchMany() {
        String[] str = {
            "https://animechan.vercel.app/api/random",
            "https://api.chucknorris.io/jokes/random",
            "https://aws.random.cat/meow",
            "https://icanhazdadjoke.com/",
            "https://goweather.herokuapp.com/weather/lyngby",
            "https://api.punkapi.com/v2/beers/random"
        };

        AnimeDTO animeDTO = null;
        ChuckDTO chuckDTO = null;
        CatDTO catDTO = null;
        JokeDTO jokeDTO = null;
        WeatherDTO weatherDTO = null;
        //Create DTO for later use
        List<BeerDTO> beerDTOS = new ArrayList<>();

        try {
            List<String> JsonResponse = HttpUtil.fetchMany(str);
            animeDTO = gson.fromJson(JsonResponse.get(0), AnimeDTO.class);
            chuckDTO = gson.fromJson(JsonResponse.get(1), ChuckDTO.class);
            catDTO = gson.fromJson(JsonResponse.get(2), CatDTO.class);
            jokeDTO = gson.fromJson(JsonResponse.get(3), JokeDTO.class);
            weatherDTO = gson.fromJson(JsonResponse.get(4), WeatherDTO.class);

            // Setup type that GSON can accept (List<BeerDTO> inside the TypeToken)
            Type type = new TypeToken<List<BeerDTO>>() {
            }.getType();

            // use that type to generate list
            beerDTOS = gson.fromJson(JsonResponse.get(5), type);
        } catch (Exception e) {
        }
        
        CombinedDTO combinedDTO = new CombinedDTO(animeDTO, weatherDTO, chuckDTO, catDTO, jokeDTO, beerDTOS);
        return gson.toJson(combinedDTO);

    }

}
