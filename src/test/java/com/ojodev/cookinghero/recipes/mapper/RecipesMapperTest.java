package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.api.model.Recipe;
import com.ojodev.cookinghero.recipes.api.model.RecipeNew;
import com.ojodev.cookinghero.recipes.data.*;
import com.ojodev.cookinghero.recipes.domain.enume.RecipeStatusEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.*;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipesMapperTest {

    @Autowired
    private RecipesMapper recipesMapper;

    @Test
    public void convertRecipeNewBOToRecipePO() {

        RecipeNewBO recipe = new RecipeNewBO(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);
        recipe.setDescription(RecipesExamples.RECIPE_01_DESCRIPTION);
        recipe.setPreparationTime(RecipesExamples.RECIPE_01_PREPARATION_TIME);
        recipe.setDifficulty(RecipesExamples.RECIPE_01_DIFFICULTY);
        String id = recipe.getId();
        DateTime creationDate = new DateTime();
        RecipePO recipePO = recipesMapper.toRecipePO(recipe, creationDate, RecipesExamples.RECIPE_01_LANGUAGE);

        assertNotNull(recipePO);
        assertNull(recipePO.getId());
        assertEquals(id, recipePO.getObjectId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipePO.getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, recipePO.getDescription());
        assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, recipePO.getPreparationTime());
        assertEquals(RecipesExamples.RECIPE_01_DIFFICULTY, recipePO.getDifficulty());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipePO.getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE.toString(), recipePO.getLanguage());
        assertEquals(creationDate, recipePO.getCreationDate());
        assertEquals(RecipeStatusEnumBO.DRAFT.toString(), recipePO.getStatus());
    }


    @Test
    public void convertRecipeNewToRecipeNewBO() {

        RecipeNew recipeNew = new RecipeNew(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, RecipesExamples.RECIPE_01_PREPARATION_TIME, RecipesExamples.RECIPE_01_DIFFICULTY, RecipesExamples.RECIPE_01_USER_ID);

        RecipeNewBO recipeNewBO = recipesMapper.toRecipeNewBO(recipeNew);

        assertNotNull(recipeNewBO);
        assertNotNull(recipeNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipeNewBO.getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, recipeNewBO.getDescription());
        assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, recipeNewBO.getPreparationTime());
        assertEquals(RecipesExamples.RECIPE_01_DIFFICULTY, recipeNewBO.getDifficulty());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipeNewBO.getUserId());
    }

    @Test
    public void convertRecipePOToRecipeBO() {

        CuisineTypePO cuisineTypePO01 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO02 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_02_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH)));

        MediaRefPO mainImage = new MediaRefPO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO.toString());

        StepPO stepPO01 = new StepPO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION);
        StepPO stepPO02 = new StepPO(StepsExamples.STEP_02_ID, StepsExamples.STEP_02_POSITION, StepsExamples.STEP_02_DESCRIPTION);
        StepPO stepPO03 = new StepPO(StepsExamples.STEP_03_ID, StepsExamples.STEP_03_POSITION, StepsExamples.STEP_03_DESCRIPTION);

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
        recipePO.setMainImage(mainImage);
        recipePO.setSteps(Arrays.asList(stepPO01, stepPO02, stepPO03));
        recipePO.setIngredients(ingredientPOList);
        recipePO.setUserId(RecipesExamples.RECIPE_01_USER_ID);
        recipePO.setCreationDate(RecipesExamples.RECIPE_01_CREATION_DATE);
        recipePO.setUpdateDate(RecipesExamples.RECIPE_01_UPDATE_DATE);
        recipePO.setStatus(RecipesExamples.RECIPE_01_STATUS_BO.toString());

        RecipeBO recipeBO = recipesMapper.toRecipeBO(recipePO);

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
        assertNotNull(recipeBO.getMainImage());
        assertEquals(MediaExamples.MEDIA_01_ID, recipeBO.getMainImage().getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, recipeBO.getMainImage().getMediaType());
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
        assertNull(recipeBO.getIngredients().get(1).getQuantity());
        assertNull(recipeBO.getIngredients().get(1).getMeasure());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipeBO.getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, recipeBO.getLanguage());
        assertEquals(RecipesExamples.RECIPE_01_CREATION_DATE, recipeBO.getCreationDate());
        assertEquals(RecipesExamples.RECIPE_01_UPDATE_DATE, recipeBO.getUpdateDate());
        assertEquals(RecipesExamples.RECIPE_01_STATUS_BO, recipeBO.getStatus());

    }

    @Test
    public void convertRecipePOToRecipeBOBasicRecipe() {

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, RecipesExamples.RECIPE_01_LANGUAGE);
        recipePO.setUserId(RecipesExamples.RECIPE_01_USER_ID);
        recipePO.setCreationDate(RecipesExamples.RECIPE_01_CREATION_DATE);
        recipePO.setStatus(RecipesExamples.RECIPE_01_STATUS_BO.toString());

        RecipeBO recipeBO = recipesMapper.toRecipeBO(recipePO);

        assertNotNull(recipeBO);
        assertEquals(RecipesExamples.RECIPE_01_ID, recipeBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipeBO.getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, recipeBO.getDescription());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipeBO.getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, recipeBO.getLanguage());
        assertEquals(RecipesExamples.RECIPE_01_CREATION_DATE, recipeBO.getCreationDate());
        assertEquals(RecipesExamples.RECIPE_01_STATUS_BO, recipeBO.getStatus());

    }


    @Test
    public void convertRecipeBOToRecipe() {

        CuisineTypeBO cuisineTypeBO01 = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        CuisineTypeBO cuisineTypeBO02 = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_02_ID, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, LanguageEnumBO.EN);

        MediaRefBO mainImage = new MediaRefBO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO);

        StepBO stepBO01 = new StepBO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION);
        StepBO stepBO02 = new StepBO(StepsExamples.STEP_02_ID, StepsExamples.STEP_02_POSITION, StepsExamples.STEP_02_DESCRIPTION);
        StepBO stepBO03 = new StepBO(StepsExamples.STEP_03_ID, StepsExamples.STEP_03_POSITION, StepsExamples.STEP_03_DESCRIPTION);

        ProductBO productBO01 = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        ProductBO productBO02 = new ProductBO(ProductsExamples.PRODUCT_02_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);

        MeasureBO measureBO = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        List<IngredientBO> ingredientBOList = Arrays.asList(new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, productBO01, IngredientsExamples.INGREDIENT_01_QUANTITY, measureBO),
                new IngredientBO(IngredientsExamples.INGREDIENT_02_ID, productBO02));

        RecipeBO recipeBO = new RecipeBO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);
        recipeBO.setDescription(RecipesExamples.RECIPE_01_DESCRIPTION);
        recipeBO.setCuisineTypes(Arrays.asList(cuisineTypeBO01, cuisineTypeBO02));
        recipeBO.setPreparationTime(RecipesExamples.RECIPE_01_PREPARATION_TIME);
        recipeBO.setDifficulty(RecipesExamples.RECIPE_01_DIFFICULTY);
        recipeBO.setMainImage(mainImage);
        recipeBO.setSteps(Arrays.asList(stepBO01, stepBO02, stepBO03));
        recipeBO.setIngredients(ingredientBOList);
        recipeBO.setUserId(RecipesExamples.RECIPE_01_USER_ID);
        recipeBO.setLanguage(RecipesExamples.RECIPE_01_LANGUAGE);
        recipeBO.setCreationDate(RecipesExamples.RECIPE_01_CREATION_DATE);
        recipeBO.setUpdateDate(RecipesExamples.RECIPE_01_UPDATE_DATE);
        recipeBO.setStatus(RecipesExamples.RECIPE_01_STATUS_BO);

        Recipe recipe = recipesMapper.toRecipe(recipeBO);

        assertNotNull(recipe);
        assertEquals(RecipesExamples.RECIPE_01_ID, recipe.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipe.getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, recipe.getDescription());
        assertNotNull(recipe.getCuisineTypes());
        assertEquals(2, recipe.getCuisineTypes().size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, recipe.getCuisineTypes().get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, recipe.getCuisineTypes().get(0).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, recipe.getCuisineTypes().get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, recipe.getCuisineTypes().get(1).getName());
        assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, recipe.getPreparationTime());
        assertEquals(RecipesExamples.RECIPE_01_DIFFICULTY, recipe.getDifficulty());
        assertNotNull(recipe.getMainImage());
        assertEquals(MediaExamples.MEDIA_01_ID, recipe.getMainImage().getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE, recipe.getMainImage().getMediaType());
        assertNotNull(recipe.getSteps());
        assertEquals(3, recipe.getSteps().size());
        assertEquals(StepsExamples.STEP_01_ID, recipe.getSteps().get(0).getId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, recipe.getSteps().get(0).getDescription());
        assertEquals(StepsExamples.STEP_02_ID, recipe.getSteps().get(1).getId());
        assertEquals(StepsExamples.STEP_02_DESCRIPTION, recipe.getSteps().get(1).getDescription());
        assertEquals(StepsExamples.STEP_03_ID, recipe.getSteps().get(2).getId());
        assertEquals(StepsExamples.STEP_03_DESCRIPTION, recipe.getSteps().get(2).getDescription());
        assertNotNull(recipe.getIngredients());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, recipe.getIngredients().get(0).getId());
        assertNotNull(recipe.getIngredients().get(0).getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, recipe.getIngredients().get(0).getProduct().getId());
        assertNotNull(recipe.getIngredients().get(0).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, recipe.getIngredients().get(0).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, recipe.getIngredients().get(0).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, recipe.getIngredients().get(0).getProduct().getStatus());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, recipe.getIngredients().get(0).getQuantity());
        assertNotNull(recipe.getIngredients().get(0).getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, recipe.getIngredients().get(0).getMeasure().getId());
        assertNotNull(recipe.getIngredients().get(0).getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, recipe.getIngredients().get(0).getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, recipe.getIngredients().get(0).getMeasure().getName().getPlural());
        assertEquals(IngredientsExamples.INGREDIENT_02_ID, recipe.getIngredients().get(1).getId());
        assertNotNull(recipe.getIngredients().get(1).getProduct());
        assertEquals(ProductsExamples.PRODUCT_02_ID, recipe.getIngredients().get(1).getProduct().getId());
        assertNotNull(recipe.getIngredients().get(1).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, recipe.getIngredients().get(1).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, recipe.getIngredients().get(1).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnum.CREATED_BY_USER, recipe.getIngredients().get(1).getProduct().getStatus());
        assertNull(recipe.getIngredients().get(1).getQuantity());
        assertNull(recipe.getIngredients().get(1).getMeasure());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipe.getUserId());
        assertEquals(RecipesExamples.RECIPE_01_CREATION_DATE, recipe.getCreationDate());
        assertEquals(RecipesExamples.RECIPE_01_UPDATE_DATE, recipe.getUpdateDate());
        assertEquals(RecipesExamples.RECIPE_01_STATUS, recipe.getStatus());

    }

    @Test
    public void convertRecipeBOToRecipeBasicRecipe() {

        RecipeBO recipeBO = new RecipeBO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);
        recipeBO.setUserId(RecipesExamples.RECIPE_01_USER_ID);
        recipeBO.setCreationDate(RecipesExamples.RECIPE_01_CREATION_DATE);
        recipeBO.setStatus(RecipesExamples.RECIPE_01_STATUS_BO);

        Recipe recipe = recipesMapper.toRecipe(recipeBO);

        assertNotNull(recipeBO);
        assertEquals(RecipesExamples.RECIPE_01_ID, recipe.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipe.getName());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipe.getUserId());
        assertEquals(RecipesExamples.RECIPE_01_CREATION_DATE, recipe.getCreationDate());
        assertEquals(RecipesExamples.RECIPE_01_STATUS, recipe.getStatus());

    }

}
