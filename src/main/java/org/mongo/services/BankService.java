package org.mongo.services;

import org.mongo.model.Bank;
import org.mongo.services.impl.CrudService;

import java.util.List;

/**
 * @author lyozniy.sergey on 19 Jul 2017.
 */
public interface BankService extends CrudService<Bank> {
    List<Bank> getByPattern(String pattern);
}
