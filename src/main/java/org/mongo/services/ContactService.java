package org.mongo.services;

import com.mongodb.DBObject;
import org.mongo.model.Contact;
import org.mongo.services.impl.CrudService;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * @author lyozniy.sergey on 19 Jul 2017.
 */
public interface ContactService extends CrudService<Contact> {
    List<Contact> getById(Collection<Long> ids);

    List<Contact> getByPattern(String pattern);

    List<DBObject> getAggregationByField(String name, String alias);

    List<Contact> getAmountAggregationFor(Pageable pageable, List<Contact> contacts, List<Contact.AggregationOptions> options);
}
