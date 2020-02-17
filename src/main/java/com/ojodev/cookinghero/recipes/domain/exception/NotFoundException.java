package com.ojodev.cookinghero.recipes.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
public class NotFoundException extends ApiException {

    public NotFoundException() {
    }

    public NotFoundException(String code, String description) {
        super(code, description);
    }


}
