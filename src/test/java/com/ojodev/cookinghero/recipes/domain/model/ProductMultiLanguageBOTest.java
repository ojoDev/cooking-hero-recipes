package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMultiLanguageBOTest {


    @Test
    public void createProductMultiLanguageBOWithDefaultLanguage() {
        DescriptiveNameBO productNameEnglish = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        List<DescriptiveNameBO> initialNames = Arrays.asList(productNameEnglish);

        ProductMultiLanguageBO product = new ProductMultiLanguageBO.Builder(
                initialNames,
                LanguageEnumBO.EN,
                ProductStatusEnumBO.APPROVED_BY_ADMIN)
                .build();

        assertNotNull(product);
        assertNotNull(product.getNames());
        assertEquals(1, product.getNames().size());
        assertEquals(productNameEnglish.getLanguage(), product.getNames().get(0).getLanguage());
        assertEquals(productNameEnglish.getSingular(), product.getNames().get(0).getSingular());
        assertEquals(productNameEnglish.getPlural(), product.getNames().get(0).getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, product.getStatus());

    }

    @Test
    public void createProductMultiLanguageBOMultipleLanguages() {
        DescriptiveNameBO productNameEnglish = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO productNameSpanish = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);

        List<DescriptiveNameBO> initialNames = Arrays.asList(productNameEnglish);

        ProductMultiLanguageBO product = new ProductMultiLanguageBO.Builder(
                initialNames,
                LanguageEnumBO.EN,
                ProductStatusEnumBO.CREATED_BY_USER)
                .name(productNameSpanish)
                .build();

        assertNotNull(product);
        assertEquals(2, product.getNames().size());
        assertEquals(productNameEnglish.getLanguage(), product.getNames().get(0).getLanguage());
        assertEquals(productNameEnglish.getSingular(), product.getNames().get(0).getSingular());
        assertEquals(productNameEnglish.getPlural(), product.getNames().get(0).getPlural());
        assertEquals(productNameSpanish.getLanguage(), product.getNames().get(1).getLanguage());
        assertEquals(productNameSpanish.getSingular(), product.getNames().get(1).getSingular());
        assertEquals(productNameSpanish.getPlural(), product.getNames().get(1).getPlural());
        assertEquals(ProductStatusEnumBO.CREATED_BY_USER, product.getStatus());
    }

    @Test
    public void createProductMultiLanguageBOMultipleLanguagesWithReplace() {
        DescriptiveNameBO productNameEnglish01 = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO productNameSpanish01 = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);
        DescriptiveNameBO productNameEnglish02 = new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO productNameSpanish02 = new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);

        List<DescriptiveNameBO> initialNames = Arrays.asList(productNameEnglish01);

        ProductMultiLanguageBO product = new ProductMultiLanguageBO.Builder(
                initialNames,
                LanguageEnumBO.EN,
                ProductStatusEnumBO.CREATED_BY_USER)
                .name(productNameSpanish01)
                .name(productNameEnglish02)
                .name(productNameSpanish02)
                .build();

        assertNotNull(product);
        assertEquals(2, product.getNames().size());
        assertEquals(productNameEnglish02.getLanguage(), product.getNames().get(0).getLanguage());
        assertEquals(productNameEnglish02.getSingular(), product.getNames().get(0).getSingular());
        assertEquals(productNameEnglish02.getPlural(), product.getNames().get(0).getPlural());
        assertEquals(productNameSpanish02.getLanguage(), product.getNames().get(1).getLanguage());
        assertEquals(productNameSpanish02.getSingular(), product.getNames().get(1).getSingular());
        assertEquals(productNameSpanish02.getPlural(), product.getNames().get(1).getPlural());
        assertEquals(ProductStatusEnumBO.CREATED_BY_USER, product.getStatus());
    }

    @Test
    public void createProductMultiLanguageBOWChangeStatus() {
        DescriptiveNameBO productNameEnglish = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        List<DescriptiveNameBO> initialNames = Arrays.asList(productNameEnglish);

        ProductMultiLanguageBO product = new ProductMultiLanguageBO.Builder(
                initialNames,
                LanguageEnumBO.EN,
                ProductStatusEnumBO.CREATED_BY_USER)
                .status(ProductStatusEnumBO.APPROVED_BY_ADMIN)
                .build();

        assertNotNull(product);
        assertNotNull(product.getNames());
        assertEquals(1, product.getNames().size());
        assertEquals(productNameEnglish.getLanguage(), product.getNames().get(0).getLanguage());
        assertEquals(productNameEnglish.getSingular(), product.getNames().get(0).getSingular());
        assertEquals(productNameEnglish.getPlural(), product.getNames().get(0).getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, product.getStatus());

    }

    @Test
    public void createProductMultiLanguageBOWithNoDefaultLanguage() {
        DescriptiveNameBO productNameSpanish = new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);
        List<DescriptiveNameBO> initialNames = Arrays.asList(productNameSpanish);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new ProductMultiLanguageBO.Builder(
                            initialNames,
                            LanguageEnumBO.EN,
                            ProductStatusEnumBO.CREATED_BY_USER)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Product needs to include default language name: en", exception.getMessage());
    }

    @Test
    public void createProductMultiLanguageBOWithInvalidFields() {
        List<DescriptiveNameBO> names = new ArrayList<>();
        names.add(new DescriptiveNameBO(null, null, null));

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new ProductMultiLanguageBO.Builder(
                            names,
                            LanguageEnumBO.EN,
                            ProductStatusEnumBO.CREATED_BY_USER)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Names needs to include singular and plural fields", exception.getMessage());
    }

    @Test
    public void createProductMultiLanguageBONoNames() {
        List<DescriptiveNameBO> names = new ArrayList<>();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new ProductMultiLanguageBO.Builder(
                            names,
                            LanguageEnumBO.EN,
                            ProductStatusEnumBO.CREATED_BY_USER)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Product needs to include at least one name and language fields", exception.getMessage());
    }

}
