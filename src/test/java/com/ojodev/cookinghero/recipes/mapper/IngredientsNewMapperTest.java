package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.IngredientNew;
import com.ojodev.cookinghero.recipes.api.model.IngredientUpdate;
import com.ojodev.cookinghero.recipes.api.model.MeasureRef;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsNewMapperTest {

    @Autowired
    private IngredientsNewMapper ingredientsNewMapper;

    @Test
    public void IngredientNewToIngredientNewBO() {

        IngredientNew ingredientNew = new IngredientNew(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, new MeasureRef(MeasuresExamples.MEASURE_01_ID));

        IngredientNewBO ingredientNewBO = ingredientsNewMapper.toIngredientNewBO(ingredientNew, RecipesExamples.RECIPE_ID_01);
        assertNotNull(ingredientNewBO);
        assertEquals(RecipesExamples.RECIPE_ID_01 + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNewBO.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientNewBO.getQuantity());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientNewBO.getMeasureId());

    }
}
