package org.mongo.services.impl;

import org.mongo.dao.CrudDao;
import org.mongo.dao.SequenceDao;
import org.mongo.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
public abstract class CrudServiceImpl<M extends Entity> implements CrudService<M> {
    @Autowired
    private SequenceDao sequenceDao;

    protected abstract String getCollectionName();

    protected abstract CrudDao<M> getCrudDao();

    public void add(M entity) {
        entity.setId(sequenceDao.getNextSequenceId(getCollectionName()));
        getCrudDao().save(entity);
    }

    @Override
    public void update(M entity) {
        getCrudDao().save(entity);
    }

    @Override
    public M get(Long id) {
        return getCrudDao().get(id);
    }

    @Override
    public List<M> getAll() {
        return getCrudDao().getAll();
    }

    @Override
    public List<M> getAll(Pageable pageable) {
        if (pageable == null) {
            return getAll();
        }
        return getCrudDao().getAll(pageable);
    }

    @Override
    public long getCount() {
        return getCrudDao().getCount();
    }

    public void remove(Long id) {
        getCrudDao().remove(id);
    }
}
