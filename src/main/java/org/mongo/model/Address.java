package org.mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Document(collection = Address.COLLECTION_NAME)
public class Address extends Entity implements Serializable, Cloneable {
    public static final String COLLECTION_NAME = "address";

    private String city;
    private String street;
    private String country;
    private String state;
    private String house;
    private Integer apartment;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}
