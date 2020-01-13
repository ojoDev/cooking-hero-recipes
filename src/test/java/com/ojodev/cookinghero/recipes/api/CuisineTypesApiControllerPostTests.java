package com.ojodev.cookinghero.recipes.api;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
public class CuisineTypesApiControllerPostTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesBusiness cuisineTypesBusiness;

    private static final String LOCALE_ENGLISH = "en";
    private static final String LOCALE_SPANISH = "es";

    private static final String INVALID_LANGUAGE = "xx";
    private static final String INVALID_NAME = "xxxxx";

    @Test
    public void postCuisineType() throws Exception {

        this.mvc.perform(post("/cuisine-types")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(CuisineTypesExamples.CUISINE_TYPE_NEW)))
                .andExpect(status().isCreated());
        }

    @Test
    public void postNotDefaultCuisineType() throws Exception {

        this.mvc.perform(post("/cuisine-types")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(CuisineTypesExamples.CUISINE_TYPE_NEW_NO_DEFALT_LANGUAGE)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.mustcontaindefault.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.mustcontaindefault.desc", LOCALE_ENGLISH))));
    }
}