package org.mongo.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

/**
 * @author lyozniy.sergey on 27 Jul 2017.
 */
@ControllerAdvice
public class CommonBindingInitializer {
    @InitBinder
    public void registerCustomEditors(WebDataBinder binder, WebRequest request) {
//        binder.registerCustomEditor(Contact.class, new ContactEditor());
//        binder.registerCustomEditor(Bank.class, new BankEditor());
    }
}
