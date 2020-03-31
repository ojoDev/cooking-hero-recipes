package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.RecipeNew;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.RecipeNewBO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
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
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesApiControllerPostTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private RecipesBusiness recipesBusiness;

    private static final String LOCALE_ENGLISH = "en";

    @Test
    public void postRecipeBasicFields() throws Exception {

        RecipeNew recipeNew = new RecipeNew();
        recipeNew.setName(RecipesExamples.RECIPE_01_NAME);

        this.mvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(recipeNew)))
                .andExpect(header().string(HttpHeaders.LOCATION, containsString("/recipes/")))
                .andExpect(status().isCreated());

        ArgumentCaptor<RecipeNewBO> argumentRecipeNew = ArgumentCaptor.forClass(RecipeNewBO.class);
        verify(recipesBusiness).addRecipe(argumentRecipeNew.capture());
        assertNotNull(argumentRecipeNew.getValue().getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, argumentRecipeNew.getValue().getName());

    }

    @Test
    public void postRecipeAllFields() throws Exception {

        RecipeNew recipeNew = new RecipeNew(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, RecipesExamples.RECIPE_01_PREPARATION_TIME, RecipesExamples.RECIPE_01_DIFFICULTY, RecipesExamples.RECIPE_01_USER_ID);

        this.mvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(recipeNew)))
                .andExpect(header().string(HttpHeaders.LOCATION, containsString("/recipes/")))
                .andExpect(status().isCreated());

        ArgumentCaptor<RecipeNewBO> argumentRecipeNew = ArgumentCaptor.forClass(RecipeNewBO.class);
        verify(recipesBusiness).addRecipe(argumentRecipeNew.capture());

        assertNotNull(argumentRecipeNew.getValue().getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, argumentRecipeNew.getValue().getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, argumentRecipeNew.getValue().getDescription());
        assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, argumentRecipeNew.getValue().getPreparationTime());
        assertEquals(RecipesExamples.RECIPE_01_DIFFICULTY, argumentRecipeNew.getValue().getDifficulty());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, argumentRecipeNew.getValue().getUserId());

    }

    @Test
    public void postNoValidRecipe() throws Exception {

        this.mvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(fileUtils.fileAsJsonString(FileNameEnum.RECIPE_POST_NO_MANDATORY_VALUES)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.code"))))
                .andExpect(jsonPath("$.description", is("name must not be null.")));

    }


}