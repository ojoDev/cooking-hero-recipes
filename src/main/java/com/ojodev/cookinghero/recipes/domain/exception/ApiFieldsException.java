package com.ojodev.cookinghero.recipes.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiFieldsException extends ApiException {

    public ApiFieldsException(String code, String description, List<FieldError> fields) {
        super(code, description);
        this.fields = fields;
    }

    public List<FieldError> fields;

}
