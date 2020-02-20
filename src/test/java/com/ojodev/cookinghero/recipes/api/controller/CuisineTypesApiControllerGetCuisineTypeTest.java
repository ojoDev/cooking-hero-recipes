package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CuisineTypesApiControllerGetCuisineTypeTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesBusiness cuisineTypesBusiness;

    private static final String LOCALE_ENGLISH = "en";
    private static final String LOCALE_SPANISH = "es";

    private static final String LOCALE_MULTIPLE_LANGUAGES = "en,de";

    private static final String INVALID_LANGUAGE = "xx";
    private static final String INVALID_ID = "xxxxx";

    @Test
    public void getCuisineType() throws Exception {

        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        when(this.cuisineTypesBusiness.getCuisineType(any(), any())).thenReturn(Optional.of(cuisineTypeBO));

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.id", is(CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(jsonPath("$.name", is(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH)));
    }

    @Test
    public void getCuisineTypeDifferentLanguages() throws Exception {

        CuisineTypeBO cuisineTypeBOEn = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        CuisineTypeBO cuisineTypeBOEs = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);

        when(this.cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.EN)).thenReturn(Optional.of(cuisineTypeBOEn));
        when(this.cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.ES)).thenReturn(Optional.of(cuisineTypeBOEs));

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.id", is(CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(jsonPath("$.name", is(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH)));

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_SPANISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_SPANISH))
                .andExpect(jsonPath("$.id", is(CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(jsonPath("$.name", is(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
    }

    @Test
    public void getCuisineTypeMultipleLanguages() throws Exception {

        CuisineTypeBO cuisineTypeBOEn = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        when(this.cuisineTypesBusiness.getCuisineType(any(), any())).thenReturn(Optional.of(cuisineTypeBOEn));

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_MULTIPLE_LANGUAGES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH));
    }

    @Test
    public void getCuisineTypeNoLanguage() throws Exception {

        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        when(this.cuisineTypesBusiness.getCuisineType(any(), any())).thenReturn(Optional.of(cuisineTypeBO));

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
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
    public void getCuisineTypeInvalidLanguage() throws Exception {

        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        when(this.cuisineTypesBusiness.getCuisineType(any(), any())).thenReturn(Optional.of(cuisineTypeBO));

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .header(HttpHeaders.ACCEPT_LANGUAGE, INVALID_LANGUAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //TODO DMS Meter interceptor general para que ponga las cabeceras correctas
                //.andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum", HttpHeaders.ACCEPT_LANGUAGE, LanguageEnum.getValueList()))));

    }

    @Test
    public void getCuisineTypeByIdNotFound() throws Exception {

        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        when(this.cuisineTypesBusiness.getCuisineType(eq(CuisineTypesExamples.CUISINE_TYPE_01_ID), any())).thenReturn(Optional.of(cuisineTypeBO));
        when(this.cuisineTypesBusiness.getCuisineType(eq(INVALID_ID), any())).thenReturn(Optional.empty());

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", INVALID_ID)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.desc"))));
    }

    @Test
    public void getCuisineTypeOutOfMemoryException() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineType(any(), any())).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/cuisine-types/{cuisine-type-id}", INVALID_ID)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc", LOCALE_ENGLISH))));
    }

}
