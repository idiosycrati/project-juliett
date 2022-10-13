package com.juliett.api.model.enums;

public enum HttpMethods {
    GET, POST, PATCH, PUT, DELETE, NULL;

    public static HttpMethods parse(String value) {
        switch (value) {
            case "GET":
                return GET;
            case "POST":
                return POST;
            case "PATCH":
                return PATCH;
            case "PUT":
                return PUT;
            case "DELETE":
                return DELETE;
            default:
                return NULL;
        }
    }
}
