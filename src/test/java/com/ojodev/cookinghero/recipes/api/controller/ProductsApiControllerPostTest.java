package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.business.ProductsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiBadRequestException;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
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

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsApiControllerPostTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private ProductsBusiness productsBusiness;

    private static final String LOCALE_ENGLISH = "en";


    @Test
    public void postProduct() throws Exception {

        ProductNew productNew = new ProductNew(Arrays.asList(
                new ProductNewName(new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL), LanguageEnum.EN),
                new ProductNewName(new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)),
                ProductStatusEnum.APPROVED_BY_ADMIN);

        this.mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(productNew)))
                .andExpect(header().string(HttpHeaders.LOCATION, endsWith("/products/" + ProductsExamples.PRODUCT_01_ID)))
                .andExpect(status().isCreated());

        verify(productsBusiness).addProduct(any());
    }

    @Test
    public void postNotDefaultProduct() throws Exception {

        ProductNew productNoDefaultLanguage = new ProductNew(Arrays.asList(
                new ProductNewName(new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)),
                ProductStatusEnum.APPROVED_BY_ADMIN);

        this.mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(productNoDefaultLanguage)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.mustcontaindefault.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.mustcontaindefault.desc", LOCALE_ENGLISH))));

        verify(productsBusiness, never()).addProduct(any());;
    }

    @Test
    public void postInvalidLanguageInProductName() throws Exception {

        this.mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(fileUtils.fileAsJsonString(FileNameEnum.PRODUCT_INVALID_LANGUAGE)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidlanguage.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidlanguage.desc", LanguageEnumBO.getValueList()))));

        verify(productsBusiness, never()).addProduct(any());
    }

    @Test
    public void postDuplicateProduct() throws Exception {

        ProductNew ProductNew = new ProductNew(Arrays.asList(
                new ProductNewName(new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL), LanguageEnum.EN),
                new ProductNewName(new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)),
                ProductStatusEnum.APPROVED_BY_ADMIN);

        doThrow(new ApiBadRequestException(messages.get("error.badrequest.duplicatedentityname.code"), messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"))).when(productsBusiness).addProduct(any());

        this.mvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(ProductNew)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.duplicatedentityname.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"))));

        verify(productsBusiness).addProduct(any());
    }

}