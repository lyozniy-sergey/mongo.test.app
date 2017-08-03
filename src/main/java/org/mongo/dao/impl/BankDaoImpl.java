package org.mongo.dao.impl;

import com.mongodb.DBObject;
import org.mongo.dao.BankDao;
import org.mongo.model.Bank;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Query.query;

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
        return mongoOperations.find(query(new Criteria().orOperator(Criteria.where("name").regex(pattern), Criteria.where("info").regex(pattern, "i"))), getPersistenceClass());
    }

    @Override
    public List<Bank> getByContactCount(int count){

        List<DBObject> data = mongoOperations.find(query(Criteria.where("contacts").size(count)), DBObject.class, Bank.COLLECTION_NAME);
        List<Bank> banks = new ArrayList<>(data.size());
        data.forEach(o -> banks.add(mongoOperations.getConverter().read(getPersistenceClass(), o)));

        return banks;
    }
}
