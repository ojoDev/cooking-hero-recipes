package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsMultipleLanguageMapperTest {

    @Autowired
    private ProductsMultipleLanguageMapper productsMultipleLanguageMapper;

    @Test
    public void convertProductNewToProductMultiLanguageBO() {

        ProductNew productNewOrigin = new ProductNew(Arrays.asList(
                new ProductNewName(new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL), LanguageEnum.EN),
                new ProductNewName(new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)),
                ProductStatusEnum.APPROVED_BY_ADMIN);

        ProductMultiLanguageBO productMultiLanguageBO = productsMultipleLanguageMapper.toProductMultiLanguageBO(productNewOrigin, LanguageEnumBO.EN);

        assertNotNull(productMultiLanguageBO);
        assertNotNull(productMultiLanguageBO.getNames());
        assertEquals(2, productMultiLanguageBO.getNames().size());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productMultiLanguageBO.getId());
        assertEquals(LanguageEnumBO.EN, productMultiLanguageBO.getNames().get(0).getLanguage());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productMultiLanguageBO.getNames().get(0).getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productMultiLanguageBO.getNames().get(0).getPlural());
        assertEquals(LanguageEnumBO.ES, productMultiLanguageBO.getNames().get(1).getLanguage());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, productMultiLanguageBO.getNames().get(1).getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, productMultiLanguageBO.getNames().get(1).getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productMultiLanguageBO.getStatus());
    }

    @Test
    public void convertProductMultiLanguageBOToProductPO() {
        DescriptiveNameBO descriptiveNameEnglish = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO descriptiveNameSpanish = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);
        ProductMultiLanguageBO productMultiLanguageBO = new ProductMultiLanguageBO.Builder(Arrays.asList(
                descriptiveNameEnglish, descriptiveNameSpanish), LanguageEnumBO.EN, ProductStatusEnumBO.APPROVED_BY_ADMIN).build();

        ProductPO productPO = productsMultipleLanguageMapper.toProductPO(productMultiLanguageBO);

        assertNotNull(productPO);
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productPO.getObjectId());
        assertNotNull(productPO.getNames());
        assertEquals(2, productPO.getNames().size());
        assertEquals(ProductsExamples.LANGUAGE_EN, productPO.getNames().get(0).getLanguage());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productPO.getNames().get(0).getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productPO.getNames().get(0).getPlural());
        assertEquals(ProductsExamples.LANGUAGE_ES, productPO.getNames().get(1).getLanguage());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, productPO.getNames().get(1).getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, productPO.getNames().get(1).getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN.toString(), productPO.getStatus());
    }
}
