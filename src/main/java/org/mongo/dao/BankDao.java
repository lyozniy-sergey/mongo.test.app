package org.mongo.dao;

import org.mongo.model.Bank;

import java.util.List;

/**
 * @author lyozniy.sergey on 18 Jul 2017.
 */
public interface BankDao extends CrudDao<Bank> {
    List<Bank> getByPattern(String pattern);
}
