package org.mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Document(collection = Address.COLLECTION_NAME)
public class Address extends Entity implements Serializable {
    public static final String COLLECTION_NAME = "address";

    private String city;
    private String street;
    private String country;
    private String state;
    private String house;
    private Integer apartment;

    private Address() {
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getHouse() {
        return house;
    }

    public Integer getApartment() {
        return apartment;
    }

    public static Builder builder() {
        return new Builder(new Address());
    }

    public static final class Builder {
        private final Address address;

        private Builder(Address address) {
            this.address = address;
        }

        public Builder setCity(String city) {
            address.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            address.street = street;
            return this;
        }

        public Builder setCountry(String country) {
            address.country = country;
            return this;
        }

        public Builder setHouse(String house) {
            address.house = house;
            return this;
        }

        public Builder setApartment(Integer apartment) {
            address.apartment = apartment;
            return this;
        }

        public Builder setState(String state) {
            address.state = state;
            return this;
        }

        public Address build() {
            return address;
        }

    }
}
