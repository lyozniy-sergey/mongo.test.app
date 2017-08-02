package org.mongo.dao;

import org.mongo.exceptions.SequenceException;

/**
 * @author lyozniy.sergey on 18 Jul 2017.
 */
public interface SequenceDao {
    Long getNextSequenceId(String key) throws SequenceException;
}
