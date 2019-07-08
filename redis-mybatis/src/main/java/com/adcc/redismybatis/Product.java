package com.adcc.redismybatis;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1435515995276255188L;
    private long id;
    private String name;
    private long price;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
