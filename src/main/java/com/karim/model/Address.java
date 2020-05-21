package com.karim.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    //-------------------- Attributes ------------------------------
    private String country ;

    private String city ;

    private String addressDet ;

    //--------------------- Constructors --------------------------
    public Address() {
    }

    public Address( String city , String country , String addressDet) {
        this.city=city;
        this.country=country;
        this.addressDet = addressDet;
    }

    //------------------------ Setter And Getter -----------------

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressDet() {
        return addressDet;
    }

    public void setAddressDet(String addressDet) {
        this.addressDet = addressDet;
    }

    //---------------- toString ---------------------
    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", addressDet='" + addressDet + '\'' +
                '}';
    }
}
