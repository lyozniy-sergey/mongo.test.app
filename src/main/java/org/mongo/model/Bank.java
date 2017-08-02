package org.mongo.model;

import org.mongo.listener.CascadeSave;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author lyozniy.sergey on 25 Jul 2017.
 */
@Document(collection = Bank.COLLECTION_NAME)
@Component
@Scope("session")
public class Bank extends Entity {
    public static final String COLLECTION_NAME = "banks";

    @DBRef
    @CascadeSave
    private Address address;

    @DBRef
    @CascadeSave
    private List<Contact> contacts;

    @Transient
    private Set<Long> newContactIds;

    private String name;
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts != null ? Collections.unmodifiableList(contacts) : null;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setContact(Contact contact) {
        initContacts(getContacts()).add(contact);
    }

    public Contact getContact() {
        return getContacts() != null && !getContacts().isEmpty() ? getContacts().get(0) : null;
    }

    private List<Contact> initContacts(List<Contact> contacts) {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        return contacts;
    }

    public Set<Long> getNewContactIds() {
        return newContactIds != null ? Collections.unmodifiableSet(newContactIds) : null;
    }

    public void setNewContactIds(Set<Long> newContactIds) {
        this.newContactIds = newContactIds;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
