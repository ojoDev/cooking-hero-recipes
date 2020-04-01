package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Product status.
 * An **Hero** can be freely define a new product as CREATED_BY_USER.
 * An **Admin** can create a new product as APPROVED_BY_ADMIN, or change (approve) a user product status. This products are show to all users to select in this recipes.
 */
public enum ProductStatusEnum {
    CREATED_BY_USER("CREATED_BY_USER"),
    APPROVED_BY_ADMIN("APPROVED_BY_ADMIN");

    private String value;

    ProductStatusEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static ProductStatusEnum fromValue(String text) {
        for (ProductStatusEnum b : ProductStatusEnum.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
