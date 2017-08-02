package org.mongo.listener;

import org.mongo.model.Address;
import org.mongo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.model.MappingException;

/**
 * @author lyozniy.sergey on 25 Jul 2017.
 */
public class ContactCascadeSaveMongoEventListener extends CascadeSaveMongoEventListener {
    @Autowired
    private AddressService addressService;

    protected void updateEntity(Object fieldValue) {
        if (fieldValue instanceof Address) {
//            Address newAddress = null;
//            try {
//                newAddress = ((Address) fieldValue).clone();
//            } catch (CloneNotSupportedException e) {
//                throw new RuntimeException(e);
//            }
            Address newAddress = (Address) fieldValue;
            Address foundAddress;
            try {
                foundAddress = addressService.getBy(newAddress);
            }catch(MappingException exc){
                foundAddress = null;
            }
            if (foundAddress == null) {
                addressService.add(newAddress);
            }else{
                newAddress.setId(foundAddress.getId());
            }
        }
    }
}
