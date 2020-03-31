package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.*;
import com.ojodev.cookinghero.recipes.domain.exception.ApiBadRequestException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
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

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IngredientsApiControllerPostTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private IngredientsBusiness ingredientsBusiness;

    private static final String LOCALE_ENGLISH = "en";


    @Test
    public void postIngredient() throws Exception {

        IngredientNew ingredientNew = new IngredientNew(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, new MeasureRef(MeasuresExamples.MEASURE_01_ID));
        String ingredientId = RecipesExamples.RECIPE_01_ID + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR;

        this.mvc.perform(post("/recipes/{recipe-id}/ingredients", RecipesExamples.RECIPE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(ingredientNew)))
                .andExpect(header().string(HttpHeaders.LOCATION, endsWith(String.format("/recipes/%s/ingredients/%s", RecipesExamples.RECIPE_01_ID, ingredientId))))
                .andExpect(status().isCreated());

        verify(ingredientsBusiness).addIngredient(any());

    }

    @Test
    public void postIngredientDuplicatedInRecipe() throws Exception {

        IngredientNew ingredientNew = new IngredientNew(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, new MeasureRef(MeasuresExamples.MEASURE_01_ID));
        String ingredientId = RecipesExamples.RECIPE_01_ID + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR;

        doThrow(new ApiBadRequestException(messages.get("error.ingredient.duplicateinrecipe.code"),messages.get("error.ingredient.duplicateinrecipe.desc", ingredientId))).when(ingredientsBusiness).addIngredient(any());

        this.mvc.perform(post("/recipes/{recipe-id}/ingredients", RecipesExamples.RECIPE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(ingredientNew)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.ingredient.duplicateinrecipe.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.ingredient.duplicateinrecipe.desc", ingredientId))));

        verify(ingredientsBusiness).addIngredient(any());

    }

    @Test
    public void postRecipeNotFound() throws Exception {
        IngredientNew ingredientNew = new IngredientNew(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, new MeasureRef(MeasuresExamples.MEASURE_01_ID));

        doThrow(new NotFoundException(messages.get("error.notfound.recipe.code"),messages.get("error.notfound.recipe.desc"))).when(ingredientsBusiness).addIngredient(any());

        this.mvc.perform(post("/recipes/{recipe-id}/ingredients", RecipesExamples.RECIPE_02_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(ingredientNew)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.recipe.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.recipe.desc"))));

        verify(ingredientsBusiness).addIngredient(any());

    }

}