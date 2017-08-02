package org.mongo.dao;

import com.mongodb.DBObject;
import org.mongo.model.Contact;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;

import java.util.Collection;
import java.util.List;

/**
 * @author lyozniy.sergey on 18 Jul 2017.
 */
public interface ContactDao extends CrudDao<Contact> {
    List<Contact> getById(Collection<Long> ids);

    List<Contact> getByPattern(String pattern);

    List<DBObject> getAggregationAmountByField(String field, String alias, Pageable pageable);

    List<DBObject> getAggregationAmountByField(String field, String alias, Pageable pageable, AggregationOperation... operations);

    List<DBObject> getAggregationAmountByField(String field, String alias);

    List<DBObject> getAggregationAmountByField(String field, String alias, AggregationOperation... operations);
}
