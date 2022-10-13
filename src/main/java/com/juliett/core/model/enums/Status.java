package com.juliett.core.model.enums;

import java.util.Arrays;

public enum Status {
    INVALID(400, "INVALID"),
    VALID(200, "VALID");

    public final Integer code;

    public final String description;

    Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Status findStatus(Integer code) {
        return Arrays.stream(Status.values()).filter(status-> code.equals(status.code)).findAny().orElse(INVALID);
    }

    @Override
    public String toString() {
        return this.description;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
