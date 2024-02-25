package org.splitwise.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntity {

    @Id
    protected int id;

    public BaseEntity(int id) {
        this.id = id;
    }

    public BaseEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
