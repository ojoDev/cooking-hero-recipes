package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CuisineTypesApiControllerGetAllTest {

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
    private static final String INVALID_NAME = "xxxxx";

    @Test
    public void getAllCuisineTypes() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), any())).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_02_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_03_ENGLISH));

        this.mvc.perform(get("/cuisine-types")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].id", is(CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(jsonPath("$[0].name", is(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH)))
                .andExpect(jsonPath("$[1].id", is(CuisineTypesExamples.CUISINE_TYPE_02_ID)))
                .andExpect(jsonPath("$[1].name", is(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH)))
                .andExpect(jsonPath("$[2].id", is(CuisineTypesExamples.CUISINE_TYPE_03_ID)))
                .andExpect(jsonPath("$[2].name", is(CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH)));
    }


    @Test
    public void getAllCuisineTypesByName() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), any())).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_02_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_03_ENGLISH));
        when(this.cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN)).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH));

        this.mvc.perform(get("/cuisine-types")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON)
                .param("name", CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(jsonPath("$[0].name", is(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH)));
    }

    @Test
    public void getAllCuisineTypesDifferentLanguages() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), eq(LanguageEnumBO.EN))).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH));
        when(this.cuisineTypesBusiness.getCuisineTypes(any(), eq(LanguageEnumBO.ES))).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_SPANISH));

        this.mvc.perform(get("/cuisine-types")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_SPANISH)
                .accept(MediaType.APPLICATION_JSON)
                .param("name", CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_SPANISH))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(jsonPath("$[0].name", is(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
    }

    @Test
    public void getAllCuisineTypesNoLanguage() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), any())).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_02_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_03_ENGLISH));

        this.mvc.perform(get("/cuisine-types")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //TODO DMS Meter interceptor general para que ponga las cabeceras correctas
//                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparamrequired.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is(HttpHeaders.ACCEPT_LANGUAGE + " " + messages.get("error.badrequest.invalidparams.fields.headerparamrequired.desc"))));

    }

    @Test
    public void getAllCuisineTypesInvalidLanguage() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), any())).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_02_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_03_ENGLISH));

        this.mvc.perform(get("/cuisine-types")
                .header(HttpHeaders.ACCEPT_LANGUAGE, INVALID_LANGUAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //TODO DMS Meter interceptor general para que ponga las cabeceras correctas
                //.andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is(HttpHeaders.ACCEPT_LANGUAGE + " " + messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum") + " " + LanguageEnum.getValueList())));

    }

    @Test
    public void getAllCuisineTypesByNameNotFound() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), any())).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_02_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_03_ENGLISH));
        when(this.cuisineTypesBusiness.getCuisineTypes(INVALID_NAME, LanguageEnumBO.EN)).thenReturn(new ArrayList<>());

        this.mvc.perform(get("/cuisine-types")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON)
                .param("name", INVALID_NAME))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.length()", is(0)));
    }

    @Test
    public void getAllCuisineTypesTypesOutOfMemoryException() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), any())).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/cuisine-types")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
    }

    @Test
    public void getMultipleLanguages() throws Exception {

        when(this.cuisineTypesBusiness.getCuisineTypes(any(), any())).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_BO_01_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_02_ENGLISH, CuisineTypesExamples.CUISINE_TYPE_BO_03_ENGLISH));

        this.mvc.perform(get("/cuisine-types")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_MULTIPLE_LANGUAGES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH));
    }

}
