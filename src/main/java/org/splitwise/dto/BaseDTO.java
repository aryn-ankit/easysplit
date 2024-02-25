package org.splitwise.dto;

public class BaseDTO {
    protected int id;

    public BaseDTO(int id) {
        this.id = id;
    }

    public BaseDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
