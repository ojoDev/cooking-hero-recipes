package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.data.*;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.RecipeBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.*;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipesBusinessGetRecipeTest {

    @Autowired
    private RecipesBusiness recipesBusiness;

    @MockBean
    private RecipesRepository recipesRepository;


    @Test
    public void getRecipe() {
        CuisineTypePO cuisineTypePO01 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO02 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_02_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH)));

        StepPO stepPO01 = new StepPO(StepsExamples.STEP_01_ID.toString(), StepsExamples.STEP_01_DESCRIPTION);
        StepPO stepPO02 = new StepPO(StepsExamples.STEP_02_ID.toString(), StepsExamples.STEP_02_DESCRIPTION);
        StepPO stepPO03 = new StepPO(StepsExamples.STEP_03_ID.toString(), StepsExamples.STEP_03_DESCRIPTION);

        ProductPO product01 = new ProductPO(ProductsExamples.PRODUCT_01_ID,
                Arrays.asList(new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                        new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductsExamples.PRODUCT_STATUS_APPROVED_BY_ADMIN);
        MeasurePO measure01 = new MeasurePO(MeasuresExamples.MEASURE_01_ID,
                Arrays.asList(new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                        new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)));
        ProductPO product02 = new ProductPO(ProductsExamples.PRODUCT_02_ID,
                Arrays.asList(new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                        new DescriptiveNamePO(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductsExamples.PRODUCT_STATUS_CREATED_BY_USER);
        List<IngredientPO> ingredientPOList = Arrays.asList(new IngredientPO(IngredientsExamples.INGREDIENT_01_ID, product01, IngredientsExamples.INGREDIENT_01_QUANTITY, measure01),
                new IngredientPO(IngredientsExamples.INGREDIENT_02_ID, product02));

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, RecipesExamples.RECIPE_01_LANGUAGE);
        recipePO.setCuisineTypes(Arrays.asList(cuisineTypePO01, cuisineTypePO02));
        recipePO.setPreparationTime(RecipesExamples.RECIPE_01_PREPARATION_TIME);
        recipePO.setDifficulty(RecipesExamples.RECIPE_01_DIFFICULTY);

        recipePO.setSteps(Arrays.asList(stepPO01, stepPO02, stepPO03));
        recipePO.setIngredients(ingredientPOList);
        recipePO.setUserId(RecipesExamples.RECIPE_01_USER_ID);
        recipePO.setCreationDate(RecipesExamples.RECIPE_01_CREATION_DATE);
        recipePO.setUpdateDate(RecipesExamples.RECIPE_01_UPDATE_DATE);
        recipePO.setStatus(RecipesExamples.RECIPE_01_STATUS_BO.toString());

        when(recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID)).thenReturn(Arrays.asList(recipePO));

        Optional<RecipeBO> recipeOpt = recipesBusiness.getRecipe(RecipesExamples.RECIPE_01_ID);
        assertNotNull(recipeOpt);
        assertTrue(recipeOpt.isPresent());
        RecipeBO recipeBO = recipeOpt.get();
        assertNotNull(recipeBO);
        assertEquals(RecipesExamples.RECIPE_01_ID, recipeBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipeBO.getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, recipeBO.getDescription());
        assertNotNull(recipeBO.getCuisineTypes());
        assertEquals(2, recipeBO.getCuisineTypes().size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, recipeBO.getCuisineTypes().get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, recipeBO.getCuisineTypes().get(0).getName());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, recipeBO.getCuisineTypes().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, recipeBO.getCuisineTypes().get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, recipeBO.getCuisineTypes().get(1).getName());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, recipeBO.getCuisineTypes().get(1).getLanguage());
        assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, recipeBO.getPreparationTime());
        assertEquals(RecipesExamples.RECIPE_01_DIFFICULTY, recipeBO.getDifficulty());
        //assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, recipeBO.getMainImage());
        assertNotNull(recipeBO.getSteps());
        assertEquals(3, recipeBO.getSteps().size());
        assertEquals(StepsExamples.STEP_01_ID, recipeBO.getSteps().get(0).getId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, recipeBO.getSteps().get(0).getDescription());
        assertEquals(StepsExamples.STEP_02_ID, recipeBO.getSteps().get(1).getId());
        assertEquals(StepsExamples.STEP_02_DESCRIPTION, recipeBO.getSteps().get(1).getDescription());
        assertEquals(StepsExamples.STEP_03_ID, recipeBO.getSteps().get(2).getId());
        assertEquals(StepsExamples.STEP_03_DESCRIPTION, recipeBO.getSteps().get(2).getDescription());
        assertNotNull(recipeBO.getIngredients());
        assertEquals(2, recipeBO.getIngredients().size());
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, recipeBO.getIngredients().get(0).getId());
        assertNotNull(recipeBO.getIngredients().get(0).getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, recipeBO.getIngredients().get(0).getProduct().getId());
        assertNotNull(recipeBO.getIngredients().get(0).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, recipeBO.getIngredients().get(0).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, recipeBO.getIngredients().get(0).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, recipeBO.getIngredients().get(0).getProduct().getStatus());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, recipeBO.getIngredients().get(0).getQuantity());
        assertNotNull(recipeBO.getIngredients().get(0).getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, recipeBO.getIngredients().get(0).getMeasure().getId());
        assertNotNull(recipeBO.getIngredients().get(0).getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, recipeBO.getIngredients().get(0).getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, recipeBO.getIngredients().get(0).getMeasure().getName().getPlural());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, recipeBO.getIngredients().get(0).getMeasure().getName().getLanguage());
        assertEquals(IngredientsExamples.INGREDIENT_02_ID, recipeBO.getIngredients().get(1).getId());
        assertNotNull(recipeBO.getIngredients().get(1).getProduct());
        assertEquals(ProductsExamples.PRODUCT_02_ID, recipeBO.getIngredients().get(1).getProduct().getId());
        assertNotNull(recipeBO.getIngredients().get(1).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, recipeBO.getIngredients().get(1).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, recipeBO.getIngredients().get(1).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnumBO.CREATED_BY_USER, recipeBO.getIngredients().get(1).getProduct().getStatus());
        Assert.assertNull(recipeBO.getIngredients().get(1).getQuantity());
        Assert.assertNull(recipeBO.getIngredients().get(1).getMeasure());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipeBO.getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, recipeBO.getLanguage());
        assertEquals(RecipesExamples.RECIPE_01_CREATION_DATE, recipeBO.getCreationDate());
        assertEquals(RecipesExamples.RECIPE_01_UPDATE_DATE, recipeBO.getUpdateDate());
        assertEquals(RecipesExamples.RECIPE_01_STATUS_BO, recipeBO.getStatus());
    }

    @Test
    public void getRecipeNotFound() {
        Optional<RecipeBO> recipe = recipesBusiness.getRecipe(RecipesExamples.RECIPE_01_ID);
        assertNotNull(recipe);
        assertFalse(recipe.isPresent());
    }
}
