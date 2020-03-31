package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.business.RecipesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.*;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesApiControllerGetRecipeTest {


    private static final String LOCALE_ENGLISH = "en" ;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private RecipesBusiness recipesBusiness;


    @Test
    public void getRecipeAllFields() throws Exception {

        CuisineTypeBO cuisineTypeBO01 = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        CuisineTypeBO cuisineTypeBO02 = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_02_ID, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, LanguageEnumBO.EN);
        //TODO DMS: Falta gestion de "Media"
        StepBO stepBO01 = new StepBO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_DESCRIPTION);
        StepBO stepBO02 = new StepBO(StepsExamples.STEP_02_ID, StepsExamples.STEP_02_DESCRIPTION);
        StepBO stepBO03 = new StepBO(StepsExamples.STEP_03_ID, StepsExamples.STEP_03_DESCRIPTION);

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        ProductBO productBO02 = new ProductBO(ProductsExamples.PRODUCT_02_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);

        MeasureBO measureBO = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        List<IngredientBO> ingredientBOList = Arrays.asList(new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, productBO01, IngredientsExamples.INGREDIENT_01_QUANTITY, measureBO),
                new IngredientBO(IngredientsExamples.INGREDIENT_02_ID, productBO02));

        RecipeBO recipeBO = new RecipeBO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);
        recipeBO.setDescription(RecipesExamples.RECIPE_01_DESCRIPTION);
        recipeBO.setCuisineTypes(Arrays.asList(cuisineTypeBO01, cuisineTypeBO02));
        recipeBO.setPreparationTime(RecipesExamples.RECIPE_01_PREPARATION_TIME);
        recipeBO.setDifficulty(RecipesExamples.RECIPE_01_DIFFICULTY);
        //recipePO.setMainImage();
        recipeBO.setSteps(Arrays.asList(stepBO01, stepBO02, stepBO03));
        recipeBO.setIngredients(ingredientBOList);
        recipeBO.setUserId(RecipesExamples.RECIPE_01_USER_ID);
        recipeBO.setLanguage(RecipesExamples.RECIPE_01_LANGUAGE);
        recipeBO.setCreationDate(RecipesExamples.RECIPE_01_CREATION_DATE);
        recipeBO.setUpdateDate(RecipesExamples.RECIPE_01_UPDATE_DATE);
        recipeBO.setStatus(RecipesExamples.RECIPE_01_STATUS_BO);

        when(this.recipesBusiness.getRecipe(RecipesExamples.RECIPE_01_ID)).thenReturn(Optional.of(recipeBO));

        this.mvc.perform(get("/recipes/{recipe-id}", RecipesExamples.RECIPE_01_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(RecipesExamples.RECIPE_01_ID)))
                .andExpect(jsonPath("$.name", is(RecipesExamples.RECIPE_01_NAME)))
                .andExpect(jsonPath("$.description", is(RecipesExamples.RECIPE_01_DESCRIPTION)))
                .andExpect(jsonPath("$.cuisineTypes[0].id", is(CuisineTypesExamples.CUISINE_TYPE_01_ID)))
                .andExpect(jsonPath("$.cuisineTypes[0].name", is(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH)))
                .andExpect(jsonPath("$.cuisineTypes[1].id", is(CuisineTypesExamples.CUISINE_TYPE_02_ID)))
                .andExpect(jsonPath("$.cuisineTypes[1].name", is(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH)))
                .andExpect(jsonPath("$.preparationTime", is(Integer.valueOf(RecipesExamples.RECIPE_01_PREPARATION_TIME.toString()))))
                .andExpect(jsonPath("$.difficulty", is(Integer.valueOf(RecipesExamples.RECIPE_01_DIFFICULTY.toString()))))
                .andExpect(jsonPath("$.steps[0].id", is(StepsExamples.STEP_01_ID)))
                .andExpect(jsonPath("$.steps[0].description", is(StepsExamples.STEP_01_DESCRIPTION)))
                .andExpect(jsonPath("$.steps[1].id", is(StepsExamples.STEP_02_ID)))
                .andExpect(jsonPath("$.steps[1].description", is(StepsExamples.STEP_02_DESCRIPTION)))
                .andExpect(jsonPath("$.ingredients[0].id", is(IngredientsExamples.INGREDIENT_01_ID)))
                .andExpect(jsonPath("$.ingredients[0].product.id", is(ProductsExamples.PRODUCT_01_ID)))
                .andExpect(jsonPath("$.ingredients[0].product.name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.ingredients[0].product.name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.ingredients[0].quantity", is(0.5)))
                .andExpect(jsonPath("$.ingredients[0].measure.id", is(MeasuresExamples.MEASURE_01_ID)))
                .andExpect(jsonPath("$.ingredients[0].measure.name.singular", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.ingredients[0].measure.name.plural", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.ingredients[1].id", is(IngredientsExamples.INGREDIENT_02_ID)))
                .andExpect(jsonPath("$.ingredients[1].product.id", is(ProductsExamples.PRODUCT_02_ID)))
                .andExpect(jsonPath("$.ingredients[1].product.name.singular", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.ingredients[1].product.name.plural", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.ingredients[1].quantity").doesNotExist())
                .andExpect(jsonPath("$.ingredients[1].measure").doesNotExist())
                .andExpect(jsonPath("$.userId", is(RecipesExamples.RECIPE_01_USER_ID)))
                .andExpect(jsonPath("$.creationDate", is(RecipesExamples.RECIPE_01_CREATION_DATE_STRING)))
                .andExpect(jsonPath("$.updateDate", is(RecipesExamples.RECIPE_01_UPDATE_DATE_STRING)))
                .andExpect(jsonPath("$.status", is(RecipesExamples.RECIPE_01_STATUS.toString())));
    }


    @Test
    public void getRecipeNotFound() throws Exception {

        when(this.recipesBusiness.getRecipe(RecipesExamples.RECIPE_01_ID)).thenReturn(Optional.empty());

        this.mvc.perform(get("/recipes/{recipe-id}", RecipesExamples.RECIPE_01_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.recipe.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.recipe.desc"))));
    }

    @Test
    public void getRecipeOutOfMemoryException() throws Exception {

        when(this.recipesBusiness.getRecipe(any())).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/recipes/{recipe-id}", RecipesExamples.RECIPE_01_ID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
    }

}
