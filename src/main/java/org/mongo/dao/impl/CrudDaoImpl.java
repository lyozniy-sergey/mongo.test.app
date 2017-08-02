package org.mongo.dao.impl;

import org.mongo.dao.CrudDao;
import org.mongo.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
public abstract class CrudDaoImpl<M extends Entity> implements CrudDao<M> {
    @Autowired
    protected MongoOperations mongoOperations;

    protected abstract Class<M> getPersistenceClass();

    @Override
    public void save(M address) {
        mongoOperations.save(address);
    }

    @Override
    public M get(Long id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), getPersistenceClass());
    }

    @Override
    public List<M> getAll() {
        return mongoOperations.findAll(getPersistenceClass());
    }

    @Override
    public List<M> getAll(Pageable pageable) {
        return mongoOperations.find(new Query().with(pageable), getPersistenceClass());
    }

    @Override
    public long getCount() {
        return mongoOperations.getCollection(mongoOperations.getCollectionName(getPersistenceClass())).count();
    }

    @Override
    public void remove(Long id) {
        mongoOperations.remove(Query.query(Criteria.where("id").is(id)), getPersistenceClass());
    }
}
