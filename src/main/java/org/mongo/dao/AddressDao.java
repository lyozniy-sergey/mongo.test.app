package org.mongo.dao;

import org.mongo.model.Address;

/**
 * @author lyozniy.sergey on 18 Jul 2017.
 */
public interface AddressDao extends CrudDao<Address> {
    Address getBy(Address address);
}
