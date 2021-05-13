/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.payment;

import javax.ws.rs.WebApplicationException;

/**
 *
 * @author peter
 */
public interface Payment {
    
    public void pay() throws WebApplicationException;
    
}
