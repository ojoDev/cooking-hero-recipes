package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IngredientsApiControllerDeleteTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private IngredientsBusiness ingredientsBusiness;

    private static final String LOCALE_ENGLISH = "en";

    @Test
    public void deleteIngredient() throws Exception {

        this.mvc.perform(delete("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(status().isNoContent());

        verify(ingredientsBusiness).deleteIngredient(RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID);
    }

    @Test
    public void deleteIngredientNotFoundRecipe() throws Exception {

        doThrow(new NotFoundException(messages.get("error.notfound.recipe.code"),messages.get("error.notfound.recipe.desc"))).when(ingredientsBusiness).deleteIngredient(RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID);

        this.mvc.perform(delete("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.recipe.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.recipe.desc"))));

        verify(ingredientsBusiness).deleteIngredient(RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID);
    }

    @Test
    public void deleteIngredientNotFoundIngredient() throws Exception {

        doThrow(new NotFoundException(messages.get("error.notfound.ingredient.code"),messages.get("error.notfound.ingredient.desc"))).when(ingredientsBusiness).deleteIngredient(RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID);

        this.mvc.perform(delete("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.ingredient.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.ingredient.desc"))));

        verify(ingredientsBusiness).deleteIngredient(RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID);
    }


}