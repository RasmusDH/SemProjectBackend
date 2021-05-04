package rest;

import dtos.RenameMeDTO;
import entities.renameme.RenameMe;
import entities.renameme.RenameMeRepository;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import rest.provider.Provider;
import facades.FacadeExample;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//Todo Remove or change relevant parts before ACTUAL use
@Path("xxx")
public class RenameMeResource extends Provider {

    private static final RenameMeRepository REPO =  FacadeExample.getFacadeExample(EMF);

    @Override
    public Response getById(int id) {
        RenameMeDTO renameMeDTO = REPO.getById(id);
        return Response.ok(GSON.toJson(renameMeDTO)).build();
    }

    @Override
    public Response getAll() {
        List<RenameMeDTO> renameMeDTOS = REPO.getAll();
        return Response.ok(GSON.toJson(renameMeDTOS)).build();
    }

    @Override
    public Response create(String jsonBody) {
        RenameMeDTO renameMeDTO = GSON.fromJson(jsonBody, RenameMeDTO.class);
        RenameMeDTO createdRenameMe = REPO.createRenameMe(renameMeDTO);
        return Response.ok(createdRenameMe).build();
    }

    @Override
    @RolesAllowed("admin")
    public Response update(int id, String jsonBody) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    @RolesAllowed("admin")
    public Response delete(int id) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}
