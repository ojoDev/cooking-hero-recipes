package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.ProductsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsBusinessGetProductsTest {


    @Autowired
    private ProductsBusiness productsBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private ProductsRepository productsRepository;


    @Test
    public void getProductsAllParams() {

        ProductPO productPO01 = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());
        ProductPO productPO02 = new ProductPO(ProductsExamples.PRODUCT_02_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());
        String name = "to";
        ProductStatusEnumBO status = ProductStatusEnumBO.APPROVED_BY_ADMIN;
        int offset = 0;
        int limit = 10;

        when(this.productsRepository.findProducts(eq(name), eq(status.toString()), any(), eq(offset), eq(limit))).thenReturn(Arrays.asList(productPO01, productPO02));

        List<ProductBO> productsEn = productsBusiness.getProducts(name, status, LanguageEnumBO.EN, offset, limit);

        assertNotNull(productsEn);
        assertEquals(2, productsEn.size());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productsEn.get(0).getId());
        assertNotNull(productsEn.get(0).getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productsEn.get(0).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productsEn.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productsEn.get(0).getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productsEn.get(0).getStatus());
        assertEquals(ProductsExamples.PRODUCT_02_ID, productsEn.get(1).getId());
        assertNotNull(productsEn.get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, productsEn.get(1).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, productsEn.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productsEn.get(1).getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productsEn.get(1).getStatus());

        List<ProductBO> productsEs = productsBusiness.getProducts(name, status, LanguageEnumBO.ES, offset, limit);

        assertNotNull(productsEs);
        assertEquals(2, productsEs.size());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productsEs.get(0).getId());
        assertNotNull(productsEs.get(0).getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, productsEs.get(0).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, productsEs.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.ES, productsEs.get(0).getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productsEn.get(0).getStatus());
        assertEquals(ProductsExamples.PRODUCT_02_ID, productsEs.get(1).getId());
        assertNotNull(productsEs.get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, productsEs.get(1).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, productsEs.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.ES, productsEs.get(1).getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, productsEn.get(1).getStatus());
    }

    @Test
    public void getProductsNoParams() {

        ProductPO productPO01 = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());
        ProductPO productPO02 = new ProductPO(ProductsExamples.PRODUCT_02_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());
        int offset = 0;
        int limit = 10;

        when(this.productsRepository.findProducts(null, null, LanguageEnumBO.EN.toString(), offset, limit)).thenReturn(Arrays.asList(productPO01, productPO02));

        List<ProductBO> productList = productsBusiness.getProducts(LanguageEnumBO.EN, offset, limit);

        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productList.get(0).getId());
        assertNotNull(productList.get(0).getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productList.get(0).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productList.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productList.get(0).getName().getLanguage());
        assertEquals(ProductsExamples.PRODUCT_02_ID, productList.get(1).getId());
        assertNotNull(productList.get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, productList.get(1).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, productList.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productList.get(1).getName().getLanguage());
    }

    @Test
    public void getProductsNoResults() {
        when(this.productsRepository.findProducts(any(), any(), any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        List<ProductBO> productList = productsBusiness.getProducts(LanguageEnumBO.EN, 0, 10);

        assertNotNull(productList);
        assertEquals(0, productList.size());
    }

    @Test
    public void countProductsWithParams() {
        when(this.productsRepository.countProducts(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductStatusEnumBO.APPROVED_BY_ADMIN.toString(), ProductsExamples.LANGUAGE_EN)).thenReturn(Long.valueOf(1L));

        Long totalProducts = productsBusiness.countProducts(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductStatusEnumBO.APPROVED_BY_ADMIN, LanguageEnumBO.EN);

        assertEquals(1L, totalProducts.longValue());
    }

}
