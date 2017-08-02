package org.mongo.model;

import org.springframework.data.annotation.Id;

/**
 * @author lyozniy.sergey on 25 Jul 2017.
 */
public abstract class Entity {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
