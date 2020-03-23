package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.ProductsRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsBusinessGetProductTest {


    @Autowired
    private ProductsBusiness productsBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private ProductsRepository productsRepository;


    @Test
    public void getProductById() {

        ProductPO productPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        when(this.productsRepository.findByObjectId(ProductsExamples.PRODUCT_01_ID)).thenReturn(Arrays.asList(productPO));

        Optional<ProductBO> productEn = productsBusiness.getProduct(ProductsExamples.PRODUCT_01_ID, LanguageEnumBO.EN);

        assertNotNull(productEn);
        assertTrue(productEn.isPresent());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productEn.get().getId());
        assertNotNull(productEn.get().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productEn.get().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productEn.get().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productEn.get().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productEn.get().getStatus());
    }

    @Test
    public void getProductDifferentLanguages() {

        ProductPO productPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        when(this.productsRepository.findByObjectId(ProductsExamples.PRODUCT_01_ID)).thenReturn(Arrays.asList(productPO));

        Optional<ProductBO> productEn = productsBusiness.getProduct(ProductsExamples.PRODUCT_01_ID, LanguageEnumBO.EN);
        assertNotNull(productEn);
        assertTrue(productEn.isPresent());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productEn.get().getId());
        assertNotNull(productEn.get().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productEn.get().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productEn.get().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productEn.get().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productEn.get().getStatus());

        Optional<ProductBO> productEs = productsBusiness.getProduct(ProductsExamples.PRODUCT_01_ID, LanguageEnumBO.ES);
        assertNotNull(productEs);
        assertTrue(productEs.isPresent());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productEs.get().getId());
        assertNotNull(productEs.get().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, productEs.get().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, productEs.get().getName().getPlural());
        assertEquals(LanguageEnumBO.ES, productEs.get().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productEs.get().getStatus());
    }

    @Test
    public void getProductByIdNotFound() {
        when(this.productsRepository.findByObjectId(ProductsExamples.PRODUCT_01_ID)).thenReturn(null);

        Optional<ProductBO> productEn = productsBusiness.getProduct(ProductsExamples.PRODUCT_01_ID, LanguageEnumBO.EN);
        assertNotNull(productEn);
        assertFalse(productEn.isPresent());
    }

}
