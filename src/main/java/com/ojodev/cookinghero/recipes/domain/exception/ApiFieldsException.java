package com.ojodev.cookinghero.recipes.domain.exception;

import java.util.List;
import java.util.Objects;


public class ApiFieldsException extends ApiException {

    private final List<FieldError> fields;

    public ApiFieldsException(String code, String description, List<FieldError> fields) {
        super(code, description);
        this.fields = fields;
    }

    public List<FieldError> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        super.toString();
        final StringBuilder sb = new StringBuilder("ApiFieldsException{");
        sb.append("fields=").append(fields);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiFieldsException that = (ApiFieldsException) o;
        return Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }
}

