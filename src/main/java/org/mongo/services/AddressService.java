package org.mongo.services;

import org.mongo.model.Address;
import org.mongo.services.impl.CrudService;

/**
 * @author lyozniy.sergey on 19 Jul 2017.
 */
public interface AddressService extends CrudService<Address> {
    Address getBy(Address address);
}
