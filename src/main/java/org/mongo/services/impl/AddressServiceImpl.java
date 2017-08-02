package org.mongo.services.impl;

import org.mongo.dao.AddressDao;
import org.mongo.dao.CrudDao;
import org.mongo.model.Address;
import org.mongo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Service
public class AddressServiceImpl extends CrudServiceImpl<Address> implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    protected String getCollectionName() {
        return Address.COLLECTION_NAME;
    }

    @Override
    protected CrudDao<Address> getCrudDao() {
        return addressDao;
    }

    @Override
    public Address getBy(Address address) {
        return addressDao.getBy(address);
    }
}
