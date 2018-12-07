package com.ojodev.cookinghero.recipes.exception;

import com.ojodev.cookinghero.recipes.bean.ApiException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-12-06T11:16:24.847Z[GMT]")

public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
