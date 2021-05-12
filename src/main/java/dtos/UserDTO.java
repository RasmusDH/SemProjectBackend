/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author peter
 */
public class UserDTO {
    
    
    private String userName;
    private String hashPassword;
    private List<RoleDTO> roles = new ArrayList<>();

    public UserDTO(String userName, String hashPassword) {
       
        this.userName = userName;
        this.hashPassword = hashPassword;
       
    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.hashPassword = user.getUserPass(); 
        this.roles = RoleDTO.getAllRoleDtoes(user.getRoleList());
    }
    
    public List<String> getRolesAsStrings() {
        if (roles.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roles.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

       public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.userName);
        hash = 53 * hash + Objects.hashCode(this.hashPassword);
        hash = 53 * hash + Objects.hashCode(this.roles);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.hashPassword, other.hashPassword)) {
            return false;
        }
      
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userName=" + userName + ", hashPassword=" + hashPassword + '}';
    }
    
    

    
    
    
}