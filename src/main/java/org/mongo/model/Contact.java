package org.mongo.model;

import org.mongo.listener.CascadeSave;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Document(collection = Contact.COLLECTION_NAME)
public class Contact extends Entity implements Serializable {
    public static final String COLLECTION_NAME = "contacts";

    private String name;
    private String lastName;
    private String number;
    private String email;
    @DBRef
    @CascadeSave
    private Address address;

    @Transient
    private Map<String, String> aggregations;

    public Contact() {
    }

    public Contact(String name, String lastName, String number, String email) {
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map getAggregations() {
        return aggregations;
    }

    public void setAggregations(Map aggregations) {
        this.aggregations = aggregations;
    }

    public void addAggregation(String key, String value) {
        if (aggregations == null) {
            aggregations = new HashMap<>();
        }
        aggregations.put(key, value);
    }

    public static Function<Contact,String> getContactName(){
        return Contact::getName;
    }

    public static Function<Contact,String> getContactLastName(){
        return Contact::getLastName;
    }

    public Builder builder() {
        return new Builder();
    }
    class Builder{

    }
    public static class AggregationOptions {
        private final String field;
        private final String alias;
        private final Function<Contact, String> function;

        public AggregationOptions(String field, String alias, Function<Contact, String> function) {
            this.field = field;
            this.alias = alias;
            this.function = function;
        }

        public String getField() {
            return field;
        }

        public String getAlias() {
            return alias;
        }

        public Function<Contact, String> getFunction() {
            return function;
        }
    }
}
