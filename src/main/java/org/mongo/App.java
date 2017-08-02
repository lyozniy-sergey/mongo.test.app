package org.mongo;

import org.mongo.config.AppConfig;
import org.mongo.exceptions.SequenceException;
import org.mongo.model.Contact;
import org.mongo.services.impl.ContactServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author lyozniy.sergey on 18 Jul 2017.
 */
public class App {
    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactServiceImpl contactServiceImpl = (ContactServiceImpl) ctx.getBean("contactServiceImpl");

        try {
            List<Contact> contacts = contactServiceImpl.getAll();
            Contact c = new Contact();
            long millis = System.currentTimeMillis()/1000000;
            String username = "user" + millis;
            c.setName(username);
            c.setEmail(username + "@i.ua");
            c.setNumber("" + (millis * 2));
            contactServiceImpl.add(c);

        } catch (SequenceException e) {
            System.out.println(e.toString());
        }

    }
}
