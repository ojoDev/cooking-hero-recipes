package com.ojodev.cookinghero.recipes.api.controller;


import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.business.RecipesBusiness_old;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IngredientsApiControllerGetIngredientTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private IngredientsBusiness ingredientsBusiness;

    @MockBean
    private RecipesBusiness_old recipesBusiness;

    @Test
    public void getIngredient() throws Exception {
        ProductBO product = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        IngredientBO ingredient = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product, BigDecimal.valueOf(2), measure);

        when(this.recipesBusiness.existsRecipe(RecipesExamples.RECIPE_01_ID)).thenReturn(true);
        when(this.ingredientsBusiness.getIngredient(RecipesExamples.RECIPE_01_ID, IngredientsExamples.INGREDIENT_01_ID)).thenReturn(Optional.of(ingredient));

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_01_ID, IngredientsExamples.INGREDIENT_01_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(IngredientsExamples.INGREDIENT_01_ID)))
                .andExpect(jsonPath("$.product.id", is(ProductsExamples.PRODUCT_01_ID)))
                .andExpect(jsonPath("$.product.name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.product.name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.product.status", is(ProductStatusEnumBO.APPROVED_BY_ADMIN.toString())))
                .andExpect(jsonPath("$.quantity", is(2)))
                .andExpect(jsonPath("$.measure.id", is(MeasuresExamples.MEASURE_01_ID)))
                .andExpect(jsonPath("$.measure.name.singular", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.measure.name.plural", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL)));
    }


    @Test
    public void getIngredientRecipeNotFound() throws Exception {

        when(this.recipesBusiness.existsRecipe(RecipesExamples.RECIPE_02_ID)).thenReturn(false);

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_02_ID, IngredientsExamples.INGREDIENT_01_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.recipe.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.recipe.desc"))));
    }

    @Test
    public void getIngredientIngredientNotFound() throws Exception {

        when(this.recipesBusiness.existsRecipe(RecipesExamples.RECIPE_02_ID)).thenReturn(true);
        when(this.ingredientsBusiness.getIngredient(RecipesExamples.RECIPE_02_ID, IngredientsExamples.INGREDIENT_01_ID)).thenReturn(Optional.empty());

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_02_ID, IngredientsExamples.INGREDIENT_01_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.ingredient.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.ingredient.desc"))));
    }

    @Test
    public void getProductOutOfMemoryException() throws Exception {

        when(this.recipesBusiness.existsRecipe(RecipesExamples.RECIPE_02_ID)).thenReturn(true);
        when(this.ingredientsBusiness.getIngredient(RecipesExamples.RECIPE_02_ID, IngredientsExamples.INGREDIENT_01_ID)).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_02_ID, IngredientsExamples.INGREDIENT_01_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
    }


}
