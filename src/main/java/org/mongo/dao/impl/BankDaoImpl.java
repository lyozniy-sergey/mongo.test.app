package org.mongo.dao.impl;

import org.mongo.dao.BankDao;
import org.mongo.model.Bank;
import org.springframework.stereotype.Repository;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Repository
public class BankDaoImpl extends CrudDaoImpl<Bank> implements BankDao {
    @Override
    protected Class<Bank> getPersistenceClass() {
        return Bank.class;
    }
}
