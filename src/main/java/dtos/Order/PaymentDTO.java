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
public class PaymentDTO {
    
    private String userName;
    private ContactInformationDTO contactInfo;
    private CreditCardDTO creditCardInfo;

    public PaymentDTO(String userName, ContactInformationDTO contactInfo, CreditCardDTO creditCardInfo) {
        this.userName = userName;
        this.contactInfo = contactInfo;
        this.creditCardInfo = creditCardInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ContactInformationDTO getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInformationDTO contactInfo) {
        this.contactInfo = contactInfo;
    }

    public CreditCardDTO getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(CreditCardDTO creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }
    
    
    
    
    
}
