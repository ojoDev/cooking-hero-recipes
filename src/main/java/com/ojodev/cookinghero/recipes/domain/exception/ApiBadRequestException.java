package com.ojodev.cookinghero.recipes.domain.exception;

public class ApiBadRequestException extends ApiException {

    public ApiBadRequestException(String code, String description) {
        super(code, description);
    }

}
