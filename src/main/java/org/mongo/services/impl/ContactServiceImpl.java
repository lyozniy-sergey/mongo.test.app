package org.mongo.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.mongo.dao.ContactDao;
import org.mongo.dao.CrudDao;
import org.mongo.model.Contact;
import org.mongo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Service
public class ContactServiceImpl extends CrudServiceImpl<Contact> implements ContactService {
    @Autowired
    private ContactDao contactDao;

    @Override
    protected String getCollectionName() {
        return Contact.COLLECTION_NAME;
    }

    @Override
    protected CrudDao<Contact> getCrudDao() {
        return contactDao;
    }

    @Override
    public List<Contact> getById(Collection<Long> ids) {
        return contactDao.getById(ids);
    }

    @Override
    public List<Contact> getByPattern(String pattern) {
        return contactDao.getByPattern(pattern);
    }

    public List<DBObject> getAggregationByField(String name, String alias) {
        return contactDao.getAggregationAmountByField(name, alias);
    }

    @Override
    public List<Contact> getAmountAggregationFor(Pageable pageable, List<Contact> contacts, List<Contact.AggregationOptions> options) {
        options.forEach(o -> {
            List<DBObject> aggregations = contactDao.getAggregationAmountByField(o.getField(), o.getAlias(), pageable);
            contacts.forEach(contact -> Contact.builder(contact).setAggregation(o.getField(), getAggregation(o.getField(), o.getAlias(), aggregations, contact.getName())).build());
        });
        return contacts;
    }

    private String getAggregation(String key, String aggKey, List<DBObject> aggregation, String value) {
        return aggregation.stream().filter(c -> value.equals(c.get(key))).findFirst().orElse(new BasicDBObject(aggKey, "1")).get(aggKey).toString();
    }
}
