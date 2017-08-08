package org.mongo.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.mongo.listener.CascadeSave;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Document(collection = Contact.COLLECTION_NAME)
public class Contact extends Entity implements Serializable {
    public static final String COLLECTION_NAME = "contacts";

    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    private String number;
    @Email
    private String email;
    @Range(min = 1, max = 150)
    private Integer age;
    @DecimalMin(value = "1", message = "table.contact.value.invalid")
    private Double balance;
    @DBRef
    @CascadeSave
    private Address address;

    @Transient
    private Map<String, String> aggregations;

    private Contact() {
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Integer getAge() {
        return age;
    }

    public Double getBalance() {
        return balance;
    }

    public Map getAggregations() {
        return aggregations != null ? Collections.unmodifiableMap(aggregations) : null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAggregations(Map<String, String> aggregations) {
        this.aggregations = aggregations;
    }

    private void setAggregation(String key, String value) {
        if (aggregations == null) {
            aggregations = new HashMap<>();
        }
        aggregations.put(key, value);
    }


    public static Function<Contact, String> getContactName() {
        return Contact::getName;
    }

    public static Function<Contact, String> getContactLastName() {
        return Contact::getLastName;
    }

    public static Builder builder() {
        return builder(new Contact());
    }

    public static Builder builder(Contact contact) {
        return new Builder(contact);
    }

    public static final class Builder {
        private final Contact contact;

        private Builder(Contact contact) {
            this.contact = contact;
        }

        public Builder setName(String name) {
            contact.setName(name);
            return this;
        }

        public Builder setLastName(String lastName) {
            contact.setLastName(lastName);
            return this;
        }

        public Builder setNumber(String number) {
            contact.setNumber(number);
            return this;
        }

        public Builder setEmail(String email) {
            contact.setEmail(email);
            return this;
        }

        public Builder setAge(Integer age) {
            contact.setAge(age);
            return this;
        }

        public Builder setBalance(Double balance) {
            contact.setBalance(balance);
            return this;
        }

        public Builder setAddress(Address address) {
            contact.setAddress(address);
            return this;
        }

        public Builder setAggregations(Map<String, String> aggregations) {
            contact.setAggregations(aggregations);
            return this;
        }

        public Builder setAggregation(String key, String value) {
            contact.setAggregation(key, value);
            return this;
        }

        public Contact build() {
            return contact;
        }
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
