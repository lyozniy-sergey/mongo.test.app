package org.mongo.model;

import org.mongo.listener.CascadeSave;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private String name;
    private String lastName;
    private String number;
    private String email;
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

    public Map getAggregations() {
        return Collections.unmodifiableMap(aggregations);
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
            contact.name = name;
            return this;
        }

        public Builder setLastName(String lastName) {
            contact.lastName = lastName;
            return this;
        }

        public Builder setNumber(String number) {
            contact.number = number;
            return this;
        }

        public Builder setEmail(String email) {
            contact.email = email;
            return this;
        }

        public Builder setAddress(Address address) {
            contact.address = address;
            return this;
        }

        public Builder setAggregations(Map<String,String> aggregations) {
            contact.aggregations = aggregations;
            return this;
        }

        public Builder setAggregation(String key, String value) {
            if (contact.aggregations == null) {
                contact.aggregations = new HashMap<>();
            }
            contact.aggregations.put(key, value);
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
