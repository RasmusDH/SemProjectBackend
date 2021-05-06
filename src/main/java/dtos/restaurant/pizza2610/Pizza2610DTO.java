/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.restaurant.pizza2610;

import dtos.restaurant.MenuDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PC
 */
public class Pizza2610DTO {
    private String name;
    private String description;
    private List<Pizza2610Menu> courses;

    public Pizza2610DTO(String name, String description, List<Pizza2610Menu> courses) {
        this.name = name;
        this.description = description;
        this.courses = courses;
    }
    
    public static List<MenuDTO> getMenuDTOsFromPizza2610Menus(List<Pizza2610Menu> menus) {
        return menus.stream()
            .map(pizza -> {
                MenuDTO menuDTO = new MenuDTO(
                    pizza.getId(),
                    pizza.getName(),
                    pizza.getPrice(),
                    pizza.getType(),
                    pizza.getDescription());
                return menuDTO;
            }).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Pizza2610Menu> getCourses() {
        return courses;
    }

    public void setCourses(List<Pizza2610Menu> courses) {
        this.courses = courses;
    }
    
}
