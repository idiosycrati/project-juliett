/**
 * Copyright (c) 2012-2020 Xurpas. All Rights Reserved.
 *
 * @author: Xurpas(doods)
 * @date: 7 Oct 2020
 * @current version: 1.0.0
 * @modified by:
 * @last modified:
 **/
package com.juliett.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.juliett.api.model.enums.ResponseCode;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class XBlastResponse {

    private String message;

    private int status;

    private Object body;

    public XBlastResponse(String message,
            int status,
            Object body) {
        // TODO Auto-generated constructor stub
        this.message = message;
        this.status  = status;
        this.body    = body;
    }

    public XBlastResponse(String message) {
        this.message = message;
    }

    public XBlastResponse(String message,
            ResponseCode responseCode) {
        // TODO Auto-generated constructor stub
        this.message = message;
        this.status  = responseCode.getValue();
    }

    public XBlastResponse(ResponseCode responseCode) {
        // TODO Auto-generated constructor stub
        this.message = responseCode.getDescription();
        this.status  = responseCode.getValue();
    }

    public XBlastResponse(ResponseCode responseCode,
            String message) {
        // TODO Auto-generated constructor stub
        this.message = message;
        this.status  = responseCode.getValue();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "XBlastResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", body=" + body +
                '}';
    }
}
