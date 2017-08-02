package org.mongo.config;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author lyozniy.sergey on 27 Jul 2017.
 */
public class ContactBeanRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
//        registry.registerCustomEditor(Bank.class, new BankEditor());
//        registry.registerCustomEditor(Contact.class, new ContactEditor());
//        registry.registerCustomEditor(List.class, new ContactEditor());
    }
}
