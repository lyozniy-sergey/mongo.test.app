package org.mongo.listener;

import org.springframework.data.mapping.model.MappingException;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

import java.util.List;

/**
 * @author lyozniy.sergey on 24 Jul 2017.
 */
public abstract class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), field -> {
            ReflectionUtils.makeAccessible(field);
            if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
                final Object fieldValue = field.get(source);
                if(fieldValue instanceof List<?>){
                    for (Object item : (List<?>)fieldValue){
                        checkField(item);
                    }
                }else{
                    checkField(fieldValue);
                }
            }
        });
    }

    private void checkField(Object fieldValue) {
        DbRefFieldCallback callback = new DbRefFieldCallback();
        if (fieldValue != null) {
            ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
            if (!callback.isIdFound()) {
                throw new MappingException("Cannot perform cascade save on child object without id set");
            }
            updateEntity(fieldValue);
        }
    }

    protected abstract void updateEntity(Object fieldValue);
}
