package org.mongo.dao.impl;

import com.mongodb.DBObject;
import org.mongo.dao.ContactDao;
import org.mongo.model.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Repository
public class ContactDaoImpl extends CrudDaoImpl<Contact> implements ContactDao {
    @Override
    protected Class<Contact> getPersistenceClass() {
        return Contact.class;
    }

    @Override
    public List<Contact> getById(Collection<Long> ids) {
        return mongoOperations.find(Query.query(Criteria.where("id").in(ids)), getPersistenceClass());
    }

    @Override
    public List<Contact> getByPattern(String pattern) {
        if (pattern == null) {
            return Collections.emptyList();
        }
//        return mongoOperations.find(Query.query(Criteria.where("name").regex(pattern).orOperator(Criteria.where("lastName").regex(pattern))), getPersistenceClass());
        return mongoOperations.find(Query.query(new Criteria().orOperator(Criteria.where("name").regex(pattern),Criteria.where("lastName").regex(pattern, "i"))), getPersistenceClass());
    }

    @Override
    public List<DBObject> getAggregationAmountByField(String field, String alias) {
        return getAggregationAmountByField(field, alias, getAggregationAmountByFieldOperation(field, alias), getAggregationProjection(field, alias));
    }

    @Override
    public List<DBObject> getAggregationAmountByField(String field, String alias, AggregationOperation... operations) {
        AggregationResults<DBObject> results = mongoOperations.aggregate(newAggregation(Contact.class, operations), DBObject.class);
        return results.getMappedResults();
    }

    @Override
    public List<DBObject> getAggregationAmountByField(String field, String alias, Pageable pageable) {
        return getAggregationAmountByField(field, alias, pageable, getAggregationAmountByFieldOperation(field, alias), getAggregationProjection(field, alias));
    }

    @Override
    public List<DBObject> getAggregationAmountByField(String field, String alias, Pageable pageable, AggregationOperation... operations) {
        AggregationOperation[] paginationOperation;
        if (pageable.getSort() != null) {
            paginationOperation = new AggregationOperation[]{sort(pageable.getSort()), skip(pageable.getPageNumber() * pageable.getPageSize()), limit(pageable.getPageSize())};
        } else {
            paginationOperation = new AggregationOperation[]{skip(pageable.getPageNumber() * pageable.getPageSize()), limit(pageable.getPageSize())};
        }
        AggregationOperation[] aggregationOperations = Stream.concat(Arrays.stream(paginationOperation), Arrays.stream(operations)).toArray(AggregationOperation[]::new);
        AggregationResults<DBObject> results = mongoOperations.aggregate(newAggregation(Contact.class, aggregationOperations), DBObject.class);
        return results.getMappedResults();
    }

    private AggregationOperation getAggregationAmountByFieldOperation(String field, String alias) {
        return group(field).count().as(alias);
    }

    private AggregationOperation getAggregationProjection(String field, String alias) {
        return project(alias).and(field).previousOperation();
    }

}
