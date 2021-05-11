package dtos;

import entities.Role;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.persistence.jpa.jpql.tools.model.query.LikeExpressionStateObject;

public class RoleDTO {

    private String roleName;


    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }

    public static List<RoleDTO> getRoleDTOList(List<Role> roles) {
        return roles.stream()
            .map(role -> new RoleDTO(role.getRoleName()))
            .collect(Collectors.toList());
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
