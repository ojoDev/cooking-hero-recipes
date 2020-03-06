package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientNewBOTest {

    @Test
    public void constructorTwoFields() {
        IngredientNewBO ingredientNewBO = new IngredientNewBO(RecipesExamples.RECIPE_ID_01, ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR);
        assertNotNull(ingredientNewBO);
        assertEquals(RecipesExamples.RECIPE_ID_01 + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_ID_01, ingredientNewBO.getRecipeId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getProductName());
        assertNull(ingredientNewBO.getQuantity());
        assertNull(ingredientNewBO.getMeasureId());
    }

    @Test
    public void constructorThreeFields() {
        IngredientNewBO ingredientNewBO = new IngredientNewBO(RecipesExamples.RECIPE_ID_01, ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY);
        assertNotNull(ingredientNewBO);
        assertEquals(RecipesExamples.RECIPE_ID_01 + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getProductName());
        assertEquals(RecipesExamples.RECIPE_ID_01, ingredientNewBO.getRecipeId());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientNewBO.getQuantity());
        assertNull(ingredientNewBO.getMeasureId());
    }

    @Test
    public void constructorFourFields() {
        IngredientNewBO ingredientNewBO = new IngredientNewBO(RecipesExamples.RECIPE_ID_01, ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, MeasuresExamples.MEASURE_01_ID);
        assertNotNull(ingredientNewBO);
        assertEquals(RecipesExamples.RECIPE_ID_01 + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_ID_01, ingredientNewBO.getRecipeId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientNewBO.getQuantity());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientNewBO.getMeasureId());
    }

    @Test
    public void constructorNullFields() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new IngredientNewBO(null, null);
                }, "Need to be throw an exception");
        assertNotNull(exception.getMessage());
    }

    @Test
    public void getterAndSetter() {
        IngredientNewBO ingredientNewBO = new IngredientNewBO(RecipesExamples.RECIPE_ID_01, ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR);
        assertNotNull(ingredientNewBO);
        ingredientNewBO.setId(RecipesExamples.RECIPE_ID_02 + "-" + ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR);
        ingredientNewBO.setRecipeId(RecipesExamples.RECIPE_ID_02);
        ingredientNewBO.setProductName(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR);
        ingredientNewBO.setQuantity(IngredientsExamples.INGREDIENT_01_QUANTITY);
        ingredientNewBO.setMeasureId(MeasuresExamples.MEASURE_02_ID);

        assertEquals(RecipesExamples.RECIPE_ID_02 + "-" + ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ingredientNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_ID_02, ingredientNewBO.getRecipeId());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ingredientNewBO.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientNewBO.getQuantity());
        assertEquals(MeasuresExamples.MEASURE_02_ID, ingredientNewBO.getMeasureId());

    }

}
