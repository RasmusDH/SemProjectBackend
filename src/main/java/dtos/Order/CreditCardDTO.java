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
public class CreditCardDTO {
    
    private String creditCardNumber;
    private String expirationDate;
    private String cardName;
    private String securityCode;

    public CreditCardDTO(String creditCardNumber, String expirationDate, String cardName, String securityCode) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cardName = cardName;
        this.securityCode = securityCode;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    
    
    
}
