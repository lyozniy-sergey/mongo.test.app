package org.mongo.dao.impl;

import org.mongo.dao.AddressDao;
import org.mongo.model.Address;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Repository
public class AddressDaoImpl extends CrudDaoImpl<Address> implements AddressDao {
    @Override
    protected Class<Address> getPersistenceClass() {
        return Address.class;
    }

    @Override
    public Address getBy(Address address) {
        return mongoOperations.findOne(
                Query.query(
                        Criteria.where("country").is(address.getCountry()).and("city").is(address.getCity()).and("street").is(address.getStreet()).
                                and("state").is(address.getState()).and("house").is(address.getHouse()).and("apartment").is(address.getApartment())
                ), getPersistenceClass());
    }

}
