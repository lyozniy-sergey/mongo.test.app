package org.mongo.dao.impl;

import org.mongo.dao.BankDao;
import org.mongo.model.Bank;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Repository
public class BankDaoImpl extends CrudDaoImpl<Bank> implements BankDao {
    @Override
    protected Class<Bank> getPersistenceClass() {
        return Bank.class;
    }

    @Override
    public List<Bank> getByPattern(String pattern) {
        if (pattern == null) {
            return Collections.emptyList();
        }
        return mongoOperations.find(Query.query(new Criteria().orOperator(Criteria.where("name").regex(pattern), Criteria.where("info").regex(pattern, "i"))), getPersistenceClass());
    }
}
