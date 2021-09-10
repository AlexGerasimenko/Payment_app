package com.my.data_base.entity;

import java.io.Serializable;

/**
 * field ID
 *
 * @author Herasimenko Olexandra
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -3821754153028012294L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
