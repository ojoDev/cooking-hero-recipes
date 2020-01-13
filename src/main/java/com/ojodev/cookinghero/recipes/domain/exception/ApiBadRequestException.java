package com.ojodev.cookinghero.recipes.domain.exception;

import lombok.Data;

@Data
public class ApiBadRequestException extends ApiException {

    public ApiBadRequestException() {
    }

    public ApiBadRequestException(String code, String description) {
        super(code, description);
    }

}
