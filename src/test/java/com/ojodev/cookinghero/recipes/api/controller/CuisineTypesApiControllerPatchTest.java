package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeUpdate;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CuisineTypesApiControllerPatchTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesBusiness cuisineTypesBusiness;


    @Test
    public void patchCuisineType() throws Exception {

        CuisineTypeUpdate cuisineTypeUpdate = new CuisineTypeUpdate(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH);

        this.mvc.perform(patch("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, CuisineTypesExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(cuisineTypeUpdate)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<CuisineTypeBO> argumentCuisineTypeUpdate = ArgumentCaptor.forClass(CuisineTypeBO.class);
        verify(cuisineTypesBusiness).addOrReplaceCuisineType(argumentCuisineTypeUpdate.capture());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, argumentCuisineTypeUpdate.getValue().getName());
        assertEquals(LanguageEnumBO.ES, argumentCuisineTypeUpdate.getValue().getLanguage());
    }

    @Test
    public void patchCuisineTypeNotFound() throws Exception {

        CuisineTypeUpdate cuisineTypeUpdate = new CuisineTypeUpdate(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH);

        doThrow(new NotFoundException(messages.get("error.notfound.code"), messages.get("error.notfound.desc"))).when(cuisineTypesBusiness).addOrReplaceCuisineType(any());

        this.mvc.perform(patch("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, CuisineTypesExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(cuisineTypeUpdate)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.desc"))));

        verify(cuisineTypesBusiness).addOrReplaceCuisineType(any());
    }

}