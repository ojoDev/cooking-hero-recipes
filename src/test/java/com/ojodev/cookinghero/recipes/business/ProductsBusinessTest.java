package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
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
public class ProductsBusinessTest {


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

        verify(productsRepository).save(any(ProductPO.class));
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

        verify(productsRepository, never()).save(any(ProductPO.class));
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

        verify(productsRepository).save(any(ProductPO.class));
    }

    @Test()
    public void addOrReplaceProductAddLanguage() {

        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        ProductPO productPOOnlyEnglish = initProductOnlyEnglish();

        when(this.productsRepository.findByObjectId(productPOOnlyEnglish.getObjectId())).thenReturn(Arrays.asList(productPOOnlyEnglish));

        Assertions.assertDoesNotThrow(() -> productsBusiness.addOrReplaceProduct(productBOEs));

        verify(productsRepository).save(any(ProductPO.class));
    }

    @Test
    public void addOrReplaceProductNoExists() {
        ProductBO productBO = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.productsRepository.findByObjectId(ProductsExamples.PRODUCT_01_ID)).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> productsBusiness.addOrReplaceProduct(productBO));
        assertEquals(messages.get("error.notfound.product.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.product.desc"), exception.getDescription());

        verify(productsRepository, never()).save(any(ProductPO.class));
    }

    @Test
    public void deleteProduct() {
        ProductPO productPO = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        when(this.productsRepository.findByObjectId(productPO.getObjectId())).thenReturn(Arrays.asList(productPO));

        Assertions.assertDoesNotThrow(() -> productsBusiness.deleteProduct(ProductsExamples.PRODUCT_01_ID), "Need to delete the resource");

        verify(productsRepository).deleteById(ProductsExamples.PRODUCT_01_ID);
    }

    @Test
    public void deleteNotFoundProduct() {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            productsBusiness.deleteProduct(ProductsExamples.PRODUCT_01_ID);
        });
        assertEquals(messages.get("error.notfound.product.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.product.desc"), exception.getDescription());

        verify(productsRepository, never()).deleteById(anyString());
    }

    private static ProductPO initProductOnlyEnglish() {
        ProductPO ProductPO = new ProductPO();
        ProductPO.setObjectId(ProductsExamples.PRODUCT_01_ID);
        ArrayList<DescriptiveNamePO> names = new ArrayList<>();
        names.add(new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN));
        ProductPO.setNames(names);
        return ProductPO;
    }

}
