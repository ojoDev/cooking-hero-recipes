package com.ojodev.cookinghero.recipes.domain.model;

/**
 * Product status.   An **Hero** can be freely define a new product as CREATED_BY_USER.   An **Admin** can create a new product as APPROVED_BY_ADMIN, or change (approve) a user product status. This products are show to all users to select in this recipes.
 */
public enum ProductStatusEnumBO {

    CREATED_BY_USER("CREATED_BY_USER"),
    APPROVED_BY_ADMIN("APPROVED_BY_ADMIN");

    private String value;

    ProductStatusEnumBO(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ProductStatusEnumBO fromValue(String text) {
        for (ProductStatusEnumBO b : ProductStatusEnumBO.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
