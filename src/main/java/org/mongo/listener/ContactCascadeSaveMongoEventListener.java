package org.mongo.listener;

import org.mongo.model.Address;
import org.mongo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lyozniy.sergey on 25 Jul 2017.
 */
public class ContactCascadeSaveMongoEventListener extends CascadeSaveMongoEventListener {
    @Autowired
    private AddressService addressService;

    protected void updateEntity(Object fieldValue) {
        if (fieldValue instanceof Address) {
            Address newAddress = (Address) fieldValue;
            Address foundAddress = addressService.getBy(newAddress);
            if (foundAddress == null) {
                addressService.add(newAddress);
            }else{
                newAddress.setId(foundAddress.getId());
            }
        }
    }
}
