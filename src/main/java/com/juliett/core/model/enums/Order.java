package com.juliett.core.model.enums;

public enum Order {
    ASC, DESC;

    public static Order find(String order) {
        switch (order == null ? "" : order.toUpperCase()) {
            case "ASC":
                return ASC;
            case "DESC":
                return DESC;
            default:
                return null;
        }
    }


}

