package org.mongo.services.impl;

import org.mongo.dao.BankDao;
import org.mongo.dao.CrudDao;
import org.mongo.model.Bank;
import org.mongo.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Service
public class BankServiceImpl extends CrudServiceImpl<Bank> implements BankService {
    @Autowired
    private BankDao bankDao;

    @Override
    protected String getCollectionName() {
        return Bank.COLLECTION_NAME;
    }

    @Override
    protected CrudDao<Bank> getCrudDao() {
        return bankDao;
    }

    @Override
    public List<Bank> getByPattern(String pattern) {
        return bankDao.getByPattern(pattern);
    }
}
