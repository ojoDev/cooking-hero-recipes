package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.business.ProductsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.endsWith;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductsApiControllerGetAllTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private ProductsBusiness productsBusiness;

    private static final String LOCALE_ENGLISH = "en";

    @Test
    public void getAllProductsDefaultValues() throws Exception {

        Integer defaultOffset = 0;
        Integer defaultLimit = 10;
        Integer totalProducts = 77;
        List<ProductBO> productList = new ArrayList<>();
        for (int i = 0 ; i < defaultLimit / 2; i++) {
            productList.add(new ProductBO(ProductsExamples.PRODUCT_01_ID + i,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN));
            productList.add(new ProductBO(ProductsExamples.PRODUCT_02_ID + i,  new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER));
        }
        when(this.productsBusiness.getProducts(any(), any(), eq(LanguageEnumBO.EN), eq(defaultOffset.intValue()), eq(defaultLimit.intValue()))).thenReturn(productList);
        when(this.productsBusiness.countProducts(any(), any(), eq(LanguageEnumBO.EN))).thenReturn(totalProducts.longValue());

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.content.length()", is(10)))
                .andExpect(jsonPath("$.content[0].id", is(ProductsExamples.PRODUCT_01_ID + 0)))
                .andExpect(jsonPath("$.content[0].name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.content[0].name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.content[0].status", is(ProductStatusEnum.APPROVED_BY_ADMIN.toString())))
                .andExpect(jsonPath("$.content[1].id", is(ProductsExamples.PRODUCT_02_ID + 0)))
                .andExpect(jsonPath("$.content[1].name.singular", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.content[1].name.plural", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.content[1].status", is(ProductStatusEnum.CREATED_BY_USER.toString())))
                .andExpect(jsonPath("$.content[2].id", is(ProductsExamples.PRODUCT_01_ID + 1)))
                .andExpect(jsonPath("$.content[3].id", is(ProductsExamples.PRODUCT_02_ID + 1)))
                .andExpect(jsonPath("$.content[4].id", is(ProductsExamples.PRODUCT_01_ID + 2)))
                .andExpect(jsonPath("$.content[5].id", is(ProductsExamples.PRODUCT_02_ID + 2)))
                .andExpect(jsonPath("$.pagination.offset", is(defaultOffset)))
                .andExpect(jsonPath("$.pagination.limit", is(defaultLimit)))
                .andExpect(jsonPath("$.pagination.page", is(1)))
                .andExpect(jsonPath("$.pagination.totalPages", is(8)))
                .andExpect(jsonPath("$.pagination.totalElements", is(totalProducts)))
                .andExpect(jsonPath("$.pagination.links.self", endsWith("/products?limit=10")))
                .andExpect(jsonPath("$.pagination.links.next", endsWith("/products?offset=10&limit=10")))
                .andExpect(jsonPath("$.pagination.links.last", endsWith("/products?offset=70&limit=10")));
    }

    @Test
    public void getAllProductsWithParams() throws Exception {

        Integer totalProducts = 77;
        String name = "po";
        ProductStatusEnumBO status = ProductStatusEnumBO.APPROVED_BY_ADMIN;
        Integer offset = 20;
        Integer limit = 10;

        List<ProductBO> productList = new ArrayList<>();
        for (int i = 0 ; i < limit; i++) {
            productList.add(new ProductBO(ProductsExamples.PRODUCT_01_ID + i ,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN));
        }

        when(this.productsBusiness.getProducts(eq(name), eq(status), eq(LanguageEnumBO.EN), eq(offset.intValue()), eq(limit.intValue()))).thenReturn(productList);
        when(this.productsBusiness.countProducts(eq(name), eq(status), eq(LanguageEnumBO.EN))).thenReturn(totalProducts.longValue());

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON)
                .param("name", name)
                .param("status", ProductStatusEnum.APPROVED_BY_ADMIN.toString())
                .param("offset", String.valueOf(offset))
                .param("limit", String.valueOf(limit)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.content.length()", is(10)))
                .andExpect(jsonPath("$.content[0].id", is(ProductsExamples.PRODUCT_01_ID + 0)))
                .andExpect(jsonPath("$.content[0].name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.content[0].name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.content[0].status", is(ProductStatusEnum.APPROVED_BY_ADMIN.toString())))
                .andExpect(jsonPath("$.pagination.offset", is(offset)))
                .andExpect(jsonPath("$.pagination.limit", is(limit)))
                .andExpect(jsonPath("$.pagination.page", is(3)))
                .andExpect(jsonPath("$.pagination.totalPages", is(8)))
                .andExpect(jsonPath("$.pagination.totalElements", is(totalProducts)))
                .andExpect(jsonPath("$.pagination.links.first",  endsWith(("/products?limit=10"))))
                .andExpect(jsonPath("$.pagination.links.prev", endsWith("/products?offset=10&limit=10")))
                .andExpect(jsonPath("$.pagination.links.self", endsWith("/products?offset=20&limit=10")))
                .andExpect(jsonPath("$.pagination.links.next", endsWith("/products?offset=30&limit=10")))
                .andExpect(jsonPath("$.pagination.links.last", endsWith("/products?offset=70&limit=10")));
    }


    @Test
    public void getProductsNoResult() throws Exception {

        when(this.productsBusiness.getProducts(any(), any(), any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsOutOfMemoryException() throws Exception {

        when(this.productsBusiness.getProducts(any(), any(), any(), anyInt(), anyInt())).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
    }



}
