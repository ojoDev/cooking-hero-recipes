package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.DescriptiveNameUpdate;
import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.api.model.ProductUpdate;
import com.ojodev.cookinghero.recipes.business.ProductsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsApiControllerPatchTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private ProductsBusiness productsBusiness;


    @Test
    public void patchProductComplete() throws Exception {

        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_02_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        ProductUpdate productUpdateComplete = new ProductUpdate(new DescriptiveNameUpdate(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL_CHANGED), ProductStatusEnum.APPROVED_BY_ADMIN);

        when(this.productsBusiness.getProduct(any(), any())).thenReturn(Optional.of(productBOEs));

        this.mvc.perform(patch("/products/{product-id}", ProductsExamples.PRODUCT_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ProductsExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(productUpdateComplete)))
                .andExpect(status().isNoContent());

        verify(productsBusiness).addOrReplaceProduct(any());
    }

    @Test
    public void patchProductPartial() throws Exception {

        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        ProductUpdate ProductUpdate = initPartialProductUpdate();

        when(this.productsBusiness.getProduct(any(), any())).thenReturn(Optional.of(productBOEs));

        this.mvc.perform(patch("/products/{product-id}", ProductsExamples.PRODUCT_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ProductsExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(ProductUpdate)))
                .andExpect(status().isNoContent());

        verify(productsBusiness).addOrReplaceProduct(any());
    }


    @Test
    public void patchProductNotFound() throws Exception {

        ProductUpdate ProductUpdateComplete = new ProductUpdate(new DescriptiveNameUpdate(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL_CHANGED), ProductStatusEnum.APPROVED_BY_ADMIN);

        when(this.productsBusiness.getProduct(any(), any())).thenReturn(Optional.empty());

        this.mvc.perform(patch("/products/{product-id}", ProductsExamples.PRODUCT_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, ProductsExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(ProductUpdateComplete)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.desc"))));

        verify(productsBusiness, never()).addOrReplaceProduct(any());
    }


    private static ProductUpdate initPartialProductUpdate() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);
        return new ProductUpdate(descriptiveNameUpdate, null);
    }


}