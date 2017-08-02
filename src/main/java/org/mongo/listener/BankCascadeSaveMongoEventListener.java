package org.mongo.listener;

import org.mongo.model.Contact;
import org.mongo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lyozniy.sergey on 25 Jul 2017.
 */
public class BankCascadeSaveMongoEventListener extends CascadeSaveMongoEventListener {
    @Autowired
    private ContactService contactService;

    protected void updateEntity(Object fieldValue) {
        if (fieldValue instanceof Contact) {
            Contact newContact = (Contact) fieldValue;
            if (newContact.getId() == null) {
                contactService.add(newContact);
            }
        }
    }
}
