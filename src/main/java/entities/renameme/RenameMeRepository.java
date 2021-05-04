package entities.renameme;

import dtos.RenameMeDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface RenameMeRepository {

    public RenameMeDTO getById(int id) throws WebApplicationException;

    public List<RenameMeDTO> getAll() throws WebApplicationException;

    public RenameMeDTO createRenameMe(RenameMeDTO renameMeDTO) throws WebApplicationException;

}
