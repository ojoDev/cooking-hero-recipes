package com.ojodev.cookinghero.recipes.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductStatusEnumBOTest {

    private static final String CREATED_BY_USER = "CREATED_BY_USER";
    private static final String APPROVED_BY_ADMIN = "APPROVED_BY_ADMIN";
    private static final String INVALID = "xx";

    @Test
    public void initFromValue(){
        ProductStatusEnumBO productStatusEnumBOEn = ProductStatusEnumBO.fromValue(CREATED_BY_USER);
        ProductStatusEnumBO productStatusEnumBOEs = ProductStatusEnumBO.fromValue(APPROVED_BY_ADMIN);

        assertEquals(ProductStatusEnumBO.CREATED_BY_USER, productStatusEnumBOEn);
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productStatusEnumBOEs);
    }

    @Test
    public void initFromInvalidValue(){
        ProductStatusEnumBO productStatusEnumBO = ProductStatusEnumBO.fromValue(INVALID);

        assertNull(productStatusEnumBO);
    }


}
