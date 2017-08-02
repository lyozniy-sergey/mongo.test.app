package org.mongo.listener;

import org.mongo.model.Bank;
import org.mongo.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lyozniy.sergey on 25 Jul 2017.
 */
public class BankCascadeSaveMongoEventListener extends CascadeSaveMongoEventListener {
    @Autowired
    private BankService bankService;

    protected void updateEntity(Object fieldValue) {
        if (fieldValue instanceof Bank) {
            bankService.add((Bank) fieldValue);
        }
    }
}
