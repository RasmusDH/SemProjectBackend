/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.user;

import entities.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class RoleDTO {
    
    String roleName;

    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }
    
     public static List<RoleDTO> getAllRoleDtoes(List<Role> roList) {
        List<RoleDTO> roDTO = new ArrayList<>();
        roList.forEach(ro -> roDTO.add(new RoleDTO(ro.getRoleName())));
        return roDTO;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    
    
}
