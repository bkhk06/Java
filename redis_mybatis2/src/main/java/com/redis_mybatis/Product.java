package com.redis_mybatis;

import java.io.Serializable;

/**
 * Created by Liu.DA on 2019/7/25
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1435515995276255188L;
    private long id;
    private String name;
    private long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
