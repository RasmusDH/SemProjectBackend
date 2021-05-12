package dtos.user;

import entities.User;
import java.util.List;

public class UserDTO {

    private String username;
    private List<RoleDTO> roles;

    public UserDTO() {
    }

    public UserDTO(String username, List<RoleDTO> roles) {
        this.username = username;
        this.roles = roles;
    }

    public UserDTO(User user) {
        this.username = user.getUserName();
        this.roles = RoleDTO.getRoleDTOList(user.getRoleList());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
