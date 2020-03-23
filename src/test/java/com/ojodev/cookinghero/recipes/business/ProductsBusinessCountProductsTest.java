package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.ProductsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsBusinessCountProductsTest {


    @Autowired
    private ProductsBusiness productsBusiness;

    @MockBean
    private ProductsRepository productsRepository;


    @Test
    public void countProductsWithParams() {
        when(this.productsRepository.countProducts(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductStatusEnumBO.APPROVED_BY_ADMIN.toString(), ProductsExamples.LANGUAGE_EN)).thenReturn(Long.valueOf(1L));

        Long totalProducts = productsBusiness.countProducts(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductStatusEnumBO.APPROVED_BY_ADMIN, LanguageEnumBO.EN);

        assertEquals(1L, totalProducts.longValue());
    }


}
