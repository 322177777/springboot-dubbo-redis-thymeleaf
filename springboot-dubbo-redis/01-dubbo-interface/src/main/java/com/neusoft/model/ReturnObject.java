package com.neusoft.model;

import java.io.Serial;
import java.io.Serializable;

public class ReturnObject implements Serializable {
    @Serial
    private static final long serialVersionUID = 3491091576633368179L;
    private String code;
    private String message;
    private Object retData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
