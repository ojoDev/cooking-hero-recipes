package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CuisineTypesApiControllerDeleteTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesBusiness cuisineTypesBusiness;

    private static final String LOCALE_ENGLISH = "en";

    @Test
    public void deleteCuisineType() throws Exception {

        this.mvc.perform(delete("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteNotFoundCuisineType() throws Exception {

        doThrow(new NotFoundException(messages.get("error.notfound.code"),messages.get("error.notfound.desc"))).when(cuisineTypesBusiness).deleteCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID);

        this.mvc.perform(delete("/cuisine-types/{cuisine-type-id}", CuisineTypesExamples.CUISINE_TYPE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.desc"))));
    }


}