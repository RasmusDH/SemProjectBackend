/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.restaurant.alcohol;

import dtos.restaurant.MenuDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author peter
 */
public class AlcoholDTO {
    
    private String name;
    private String description;
    private List<AlcoholMenu> courses;

    public AlcoholDTO(String name, String description,
        List<AlcoholMenu> courses) {
        this.name = name;
        this.description = description;
        this.courses = courses;
    }
    
    
     public static List<MenuDTO> getMenuDTOsFromAlcoholMenus(List<AlcoholMenu> menus) {
        return menus.stream()
            .map(alcohol -> {
                MenuDTO menuDTO = new MenuDTO(
                    alcohol.getId(),
                    alcohol.getName(),
                    alcohol.getPrice(),
                    alcohol.getType(),
                    alcohol.getDescription());
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

    public List<AlcoholMenu> getCourses() {
        return courses;
    }

    public void setCourses(List<AlcoholMenu> courses) {
        this.courses = courses;
    }

    
    
}


/*

package dtos.restaurant.bananaleaf;

import dtos.restaurant.MenuDTO;
import java.util.List;
import java.util.stream.Collectors;

public class BananaLeafDTO {

    private String restaurant;
    private String decription;
    private List<BananaLeafMenu> menus;

    public BananaLeafDTO(String name, String description,
        List<BananaLeafMenu> bananaLeafMenus) {
        this.restaurant = name;
        this.decription = description;
        this.menus = bananaLeafMenus;
    }

    // We do this so that we can put it on the RestaurantDTO
    public static List<MenuDTO> getMenuDTOsFromBananaLeafMenus(List<BananaLeafMenu> menus) {
        return menus.stream()
            .map(banana -> {
                MenuDTO menuDTO = new MenuDTO(banana.getId(),
                    banana.getItemName(),
                    banana.getPrice(),
                    banana.getCategory(),
                    banana.getDescription());
                return menuDTO;
            }).collect(Collectors.toList());
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public List<BananaLeafMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<BananaLeafMenu> menus) {
        this.menus = menus;
    }
}




*/