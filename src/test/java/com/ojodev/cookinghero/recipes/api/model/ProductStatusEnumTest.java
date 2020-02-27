package com.ojodev.cookinghero.recipes.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductStatusEnumTest {

    private static final String CREATED_BY_USER = "CREATED_BY_USER";
    private static final String APPROVED_BY_ADMIN = "APPROVED_BY_ADMIN";
    private static final String INVALID = "xx";

    @Test
    public void initFromValue() {

        ProductStatusEnum productStatusCreatedByUser = ProductStatusEnum.fromValue(CREATED_BY_USER);
        ProductStatusEnum productStatusApprovedByAdmin = ProductStatusEnum.fromValue(APPROVED_BY_ADMIN);

        assertEquals(ProductStatusEnum.CREATED_BY_USER, productStatusCreatedByUser);
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productStatusApprovedByAdmin);
    }

    @Test
    public void initFromInvalidValue() {
        ProductStatusEnum productStatusInvalid = ProductStatusEnum.fromValue(INVALID);

        assertNull(productStatusInvalid);
    }

}
