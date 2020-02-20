package com.ojodev.cookinghero.recipes.domain.exception;

@SuppressWarnings("serial")
public class NotFoundException extends ApiException {

    public NotFoundException() {
    }

    public NotFoundException(String code, String description) {
        super(code, description);
    }


}
