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
public class ProductsBusinessAddProductTest {


    @Autowired
    private ProductsBusiness productsBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private ProductsRepository productsRepository;


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

}
