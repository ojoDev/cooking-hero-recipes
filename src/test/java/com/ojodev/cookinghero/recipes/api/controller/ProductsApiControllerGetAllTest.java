package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
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

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
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
    private static final String LOCALE_SPANISH = "es";

    private static final String LOCALE_MULTIPLE_LANGUAGES = "en,de";

    private static final String INVALID_LANGUAGE = "xx";


    @Test
    public void getAllProductsNoParams() throws Exception {

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductBO productBO02 = new ProductBO(ProductsExamples.PRODUCT_02_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);

        when(this.productsBusiness.getProducts(any(), eq(LanguageEnumBO.EN), anyInt(), anyInt())).thenReturn(Arrays.asList(productBO01, productBO02));

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(ProductsExamples.PRODUCT_01_ID)))
                .andExpect(jsonPath("$[0].name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$[0].name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$[0].status", is(ProductStatusEnumBO.CREATED_BY_USER.toString())))
                .andExpect(jsonPath("$[1].id", is(ProductsExamples.PRODUCT_02_ID)))
                .andExpect(jsonPath("$[1].name.singular", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$[1].name.plural", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$[1].status", is(ProductStatusEnumBO.APPROVED_BY_ADMIN.toString())));
    }

    @Test
    public void getAllProductsSkipLimitParams() throws Exception {

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductBO productBO02 = new ProductBO(ProductsExamples.PRODUCT_02_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);

        when(this.productsBusiness.getProducts(any(), eq(LanguageEnumBO.EN), eq(0), eq(10))).thenReturn(Arrays.asList(productBO01, productBO02));
        when(this.productsBusiness.getProducts(any(), eq(LanguageEnumBO.EN), eq(10), eq(50))).thenReturn(Arrays.asList());

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON)
                .param("skip", "10")
                .param("limit", "50"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(ProductsExamples.PRODUCT_01_ID)))
                .andExpect(jsonPath("$[0].name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$[0].name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$[0].status", is(ProductStatusEnumBO.CREATED_BY_USER.toString())))
                .andExpect(jsonPath("$[1].id", is(ProductsExamples.PRODUCT_02_ID)))
                .andExpect(jsonPath("$[1].name.singular", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$[1].name.plural", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$[1].status", is(ProductStatusEnumBO.APPROVED_BY_ADMIN.toString())));
    }

    @Test
    public void getAllProductsNameSkipLimitNameParams() throws Exception {

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductBO productBO02 = new ProductBO(ProductsExamples.PRODUCT_02_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        String searchName = "po";
        
        when(this.productsBusiness.getProducts(any(), eq(LanguageEnumBO.EN), eq(0), eq(10))).thenReturn(Arrays.asList(productBO01, productBO02));
        when(this.productsBusiness.getProducts(eq(searchName), eq(LanguageEnumBO.EN), eq(0), eq(10))).thenReturn(Arrays.asList(productBO01));

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON)
                .param("skip", "10")
                .param("limit", "10")
                .param("name", searchName))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(ProductsExamples.PRODUCT_01_ID)))
                .andExpect(jsonPath("$[0].name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$[0].name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$[0].status", is(ProductStatusEnumBO.CREATED_BY_USER.toString())));
    }


    @Test
    public void getAllProductsDifferentLanguages() throws Exception {

        ProductBO productBOEn = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductBO productBOEs = new ProductBO(ProductsExamples.PRODUCT_02_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, LanguageEnumBO.ES), ProductStatusEnumBO.APPROVED_BY_ADMIN);

        when(this.productsBusiness.getProducts(any(), eq(LanguageEnumBO.EN), eq(0), eq(10))).thenReturn(Arrays.asList(productBOEn));
        when(this.productsBusiness.getProducts(any(), eq(LanguageEnumBO.ES), eq(0), eq(10))).thenReturn(Arrays.asList(productBOEs));

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_SPANISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_SPANISH))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(ProductsExamples.PRODUCT_02_ID)))
                .andExpect(jsonPath("$[0].name.singular", is(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR)))
                .andExpect(jsonPath("$[0].name.plural", is(ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL)))
                .andExpect(jsonPath("$[0].status", is(ProductStatusEnumBO.APPROVED_BY_ADMIN.toString())));;
    }

    @Test
    public void getAllProductsMultipleLanguages() throws Exception {

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);

        when(this.productsBusiness.getProducts(any(), any(), anyInt(), anyInt())).thenReturn(Arrays.asList(productBO01));

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_MULTIPLE_LANGUAGES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH));
    }

    @Test
    public void getAllProductsNoLanguage() throws Exception {

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductBO productBO02 = new ProductBO(ProductsExamples.PRODUCT_02_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);

        when(this.productsBusiness.getProducts(any(), any(), anyInt(), anyInt())).thenReturn(Arrays.asList(productBO01, productBO02));

        this.mvc.perform(get("/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //TODO DMS Meter interceptor general para que ponga las cabeceras correctas
//                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparamrequired.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is(messages.get("error.badrequest.invalidparams.fields.headerparamrequired.desc", HttpHeaders.ACCEPT_LANGUAGE))));

    }

    @Test
    public void getAllProductsInvalidLanguage() throws Exception {

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        ProductBO productBO02 = new ProductBO(ProductsExamples.PRODUCT_02_ID,  new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);

        when(this.productsBusiness.getProducts(any(), any(), anyInt(), anyInt())).thenReturn(Arrays.asList(productBO01, productBO02));

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, INVALID_LANGUAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //TODO DMS Meter interceptor general para que ponga las cabeceras correctas
                //.andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is( messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum", HttpHeaders.ACCEPT_LANGUAGE, LanguageEnum.getValueList()))));

    }

    @Test
    public void getProductsOutOfMemoryException() throws Exception {

        when(this.productsBusiness.getProducts(any(), any(), anyInt(), anyInt())).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/products")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
    }


}
