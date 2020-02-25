package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductStatusEnumMapperTest {


    @Autowired
    private ProductStatusEnumMapper productStatusEnumMapper;

    @Test
    public void convertProductStatusEnumToProductStatusEnumBO() {
        ProductStatusEnumBO productStatusCreatedByUserBO = productStatusEnumMapper.toProductStatusEnumBO(ProductStatusEnum.CREATED_BY_USER);
        assertNotNull(productStatusCreatedByUserBO);
        assertEquals(ProductStatusEnumBO.CREATED_BY_USER, productStatusCreatedByUserBO);

        ProductStatusEnumBO productStatusApprovedByAdminBO = productStatusEnumMapper.toProductStatusEnumBO(ProductStatusEnum.APPROVED_BY_ADMIN);
        assertNotNull(productStatusApprovedByAdminBO);
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productStatusApprovedByAdminBO);
    }

    @Test
    public void convertProductStatusEnumBOToProductStatusEnum() {
        ProductStatusEnum productStatusCreatedByUser = productStatusEnumMapper.toProductStatusEnum(ProductStatusEnumBO.CREATED_BY_USER);
        assertNotNull(productStatusCreatedByUser);
        assertEquals(ProductStatusEnum.CREATED_BY_USER, productStatusCreatedByUser);

        ProductStatusEnum productStatusApprovedByAdmin = productStatusEnumMapper.toProductStatusEnum(ProductStatusEnumBO.APPROVED_BY_ADMIN);
        assertNotNull(productStatusApprovedByAdmin);
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productStatusApprovedByAdmin);
    }
}
