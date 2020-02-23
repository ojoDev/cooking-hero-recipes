package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.ProductsRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsBusinessTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private ProductsBusiness productsBusiness;

    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void getAllProductsByLanguageLimitSkip() {

        ProductPO productPO01 = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.CREATED_BY_USER.toString());
        ProductPO productPO02 = new ProductPO(ProductsExamples.PRODUCT_02_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        when(this.productsRepository.findByName("", "en", 0, 10)).thenReturn(Arrays.asList(productPO01, productPO02));

        List<ProductBO> productEn = productsBusiness.getProducts(LanguageEnumBO.EN, 0, 10) ;

        assertNotNull(productEn);
        assertEquals(2, productEn.size());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productEn.get(0).getId());
        assertNotNull(productEn.get(0).getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, productEn.get(0).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, productEn.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productEn.get(0).getName().getLanguage());
        assertEquals(ProductStatusEnum.CREATED_BY_USER, productEn.get(0).getStatus());

        assertEquals(ProductsExamples.PRODUCT_02_ID, productEn.get(1).getId());
        assertNotNull(productEn.get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, productEn.get(1).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, productEn.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, productEn.get(1).getName().getLanguage());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productEn.get(0).getStatus());


        List<ProductBO> productEs = productsBusiness.getProducts(LanguageEnumBO.EN, 0, 10) ;

        assertNotNull(productEs);
        assertEquals(2, productEs.size());
        assertEquals(ProductsExamples.PRODUCT_01_ID, productEs.get(0).getId());
        assertNotNull(productEs.get(0).getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, productEs.get(0).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, productEs.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.ES, productEs.get(0).getName().getLanguage());
        assertEquals(ProductStatusEnum.CREATED_BY_USER, productEs.get(0).getStatus());

        assertEquals(ProductsExamples.PRODUCT_02_ID, productEs.get(1).getId());
        assertNotNull(productEs.get(1).getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, productEs.get(1).getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, productEs.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.ES, productEs.get(1).getName().getLanguage());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, productEs.get(0).getStatus());
    }


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

    @Test
    public void addNewProduct() {

        ProductMultiLanguageBO productMultiLanguageBO = new ProductMultiLanguageBO.Builder(Arrays.asList(
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN),
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.EN)),
                LanguageEnumBO.EN,
                ProductStatusEnumBO.APPROVED_BY_ADMIN).build();

        when(this.productsRepository.findByObjectId(productMultiLanguageBO.getId())).thenReturn(null);
        when(this.productsRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> productsBusiness.addProduct(productMultiLanguageBO));
    }

    @Test
    public void addExistentProducts() {

        ProductPO productPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        ProductMultiLanguageBO productMultiLanguageBO = new ProductMultiLanguageBO.Builder(Arrays.asList(
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN),
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.EN)),
                LanguageEnumBO.EN,
                ProductStatusEnumBO.APPROVED_BY_ADMIN).build();

        when(this.productsRepository.findByObjectId(productMultiLanguageBO.getId())).thenReturn(Arrays.asList(productPO));

        ApiException e = Assertions.assertThrows(ApiException.class, () -> {
            productsBusiness.addProduct(productMultiLanguageBO);
        });

        assertEquals(messages.get("error.badrequest.duplicatedentityname.code"), e.getCode());
        assertEquals(messages.get("error.badrequest.duplicatedentityname.desc", "product"), e.getDescription());
    }

    @Test()
    public void addOrReplaceProductReplace() {

        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        ProductPO productPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        when(this.productsRepository.findByObjectId(productPO.getObjectId())).thenReturn(Arrays.asList(productPO));

        Assertions.assertDoesNotThrow(() -> productsBusiness.addOrReplaceProduct(productBOEs));
    }

    @Test()
    public void addOrReplaceProductAddLanguage() {

        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        ProductPO productPOOnlyEnglish = initMeasureOnlyEnglish();

        when(this.productsRepository.findByObjectId(productPOOnlyEnglish.getObjectId())).thenReturn(Arrays.asList(productPOOnlyEnglish));

        Assertions.assertDoesNotThrow(() -> productsBusiness.addOrReplaceProduct(productBOEs));
    }

    @Test
    public void addOrReplaceProductNoExists() {
        ProductBO productBO = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.productsRepository.findByObjectId(ProductsExamples.PRODUCT_01_ID)).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> productsBusiness.addOrReplaceProduct(productBO));
        assertEquals(messages.get("error.notfound.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.desc"), exception.getDescription());
    }

    @Test
    public void deleteProduct() {
        ProductPO productPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        when(this.productsRepository.findByObjectId(productPO.getObjectId())).thenReturn(Arrays.asList(productPO));

        Assertions.assertDoesNotThrow(() -> productsBusiness.deleteProduct(ProductsExamples.PRODUCT_01_ID), "Need to delete the resource");
    }

    @Test
    public void deleteNotFoundProduct() {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            productsBusiness.deleteProduct(ProductsExamples.PRODUCT_01_ID);
        });
        assertEquals(messages.get("error.notfound.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.desc"), exception.getDescription());
    }

    private static ProductPO initMeasureOnlyEnglish() {
        ProductPO ProductPO = new ProductPO();
        ProductPO.setObjectId(ProductsExamples.PRODUCT_01_ID);
        ArrayList<DescriptiveNamePO> names = new ArrayList<>();
        names.add(new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN));
        ProductPO.setNames(names);
        return ProductPO;
    }

}
