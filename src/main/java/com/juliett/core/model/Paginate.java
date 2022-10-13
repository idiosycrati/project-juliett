package com.juliett.core.model;

import java.util.List;

public class Paginate<TList extends List<?>> {
    TList data;
    Integer total;

    public Paginate(TList data, Integer total) {
        this.data  = data;
        this.total = total;
    }

    public Paginate() {
    }

    public TList getData() {
        return data;
    }

    public void setData(TList data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Paginate{" +
                "data=" + data +
                ", total=" + total +
                '}';
    }
}
