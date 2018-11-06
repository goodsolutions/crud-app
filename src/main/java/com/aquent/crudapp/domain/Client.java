package com.aquent.crudapp.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The client entity corresponding to the "client" table in the database.
 */

public class Client {

    private Integer clientId;
    
    @NotNull
    @Size(min = 1, max = 50, message = "Client name is required with maximum length of 50")
    private String clientName;
    
    @NotNull
    @Size(min = 1, max = 200, message = "Client URI is required with maximum length of 200")
    private String clientUri;
    
    @NotNull
    @Size(min = 1, max = 20, message = "Phone number is required with maximum length of 20")
    private String phoneNumber;

    @NotNull
    @Size(min = 1, max = 50, message = "Street address is required with maximum length of 50")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 50, message = "City is required with maximum length of 50")
    private String city;

    @NotNull
    @Size(min = 2, max = 2, message = "State is required with length 2")
    private String state;

    @NotNull
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    private String zipCode;
    

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }      

    public String getClientUri() {
        return clientUri;
    }

    public void setClientUri(String clientUri) {
        this.clientUri = clientUri;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    

}
