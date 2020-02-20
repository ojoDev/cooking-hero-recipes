package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNewName;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.domain.exception.ApiBadRequestException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
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

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CuisineTypesApiControllerPostTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private CuisineTypesBusiness cuisineTypesBusiness;

    private static final String LOCALE_ENGLISH = "en";
    @Test
    public void postCuisineType() throws Exception {

        CuisineTypeNew cuisineTypeNew = new CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnum.EN),
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES)));

        this.mvc.perform(post("/cuisine-types")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(cuisineTypeNew)))
                .andExpect(header().string(HttpHeaders.LOCATION, endsWith("/cuisine-types/" + CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(status().isCreated());
        }

    @Test
    public void postNotDefaultCuisineType() throws Exception {

        CuisineTypeNew cuisineTypeNewNoDefaultLanguage = new  CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES)));

        this.mvc.perform(post("/cuisine-types")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(cuisineTypeNewNoDefaultLanguage)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.mustcontaindefault.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.mustcontaindefault.desc", LOCALE_ENGLISH))));
    }

    @Test
    public void postInvalidLanguageInCuisineTypeName() throws Exception {

        this.mvc.perform(post("/cuisine-types")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(fileUtils.fileAsJsonString(FileNameEnum.CUISINE_TYPE_INVALID_LANGUAGE)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidlanguage.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidlanguage.desc", LanguageEnumBO.getValueList()))));
    }

    @Test
    public void postDuplicateCuisineType() throws Exception {

        CuisineTypeNew cuisineTypeNew = new CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnum.EN),
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES)));

        doThrow(new ApiBadRequestException(messages.get("error.badrequest.duplicatedentityname.code"), messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"))).when(cuisineTypesBusiness).addCuisineType(any());

        this.mvc.perform(post("/cuisine-types")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(cuisineTypeNew)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.duplicatedentityname.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"))));
    }

}