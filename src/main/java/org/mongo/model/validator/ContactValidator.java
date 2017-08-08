package org.mongo.model.validator;

import org.mongo.model.Contact;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * @author lyozniy.sergey on 08 Aug 2017.
 */
@Component
public class ContactValidator implements Validator {
    /**
     * This Validator validates just Person instances
     */
    @Override
    public boolean supports(Class clazz) {
        return Contact.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "table.contact.empty");
        Contact p = (Contact) obj;
        if (p.getAge() < 0) {
            e.rejectValue("age", "table.contact.age.negative");
        } else if (p.getAge() > 110) {
            e.rejectValue("age", "table.contact.age.too.old");
        }
    }
}
