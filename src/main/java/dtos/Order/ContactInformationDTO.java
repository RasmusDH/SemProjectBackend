/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.Order;

/**
 *
 * @author peter
 */
public class ContactInformationDTO {
    
    private String name;
    private String email;
    private String phone;
    private String address;
    private String delivery;

    public ContactInformationDTO(String name, String email, String phone, String address, String delivery) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
    
    
    
}
