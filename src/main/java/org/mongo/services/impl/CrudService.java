package org.mongo.services.impl;

import org.mongo.model.Entity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lyozniy.sergey on 25 Jul 2017.
 */
public interface CrudService<M extends Entity> {
    void add(M contact);

    void update(M contact);

    M get(Long id);

    List<M> getAll();

    List<M> getAll(Pageable pageable);

    long getCount();

    void remove(Long id);
}
