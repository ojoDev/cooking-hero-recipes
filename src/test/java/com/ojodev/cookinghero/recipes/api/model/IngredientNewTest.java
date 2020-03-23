package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientNewTest extends AbstractJavaBeanTest<IngredientNew> {

    @Override
    protected IngredientNew getBeanInstance() {
        return new IngredientNew();
    }

    @Test
    public void constructorAllFields() {
        IngredientNew ingredientNew = new IngredientNew(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, new MeasureRef(MeasuresExamples.MEASURE_01_ID));

        assertNotNull(ingredientNew);
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientNew.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientNew.getQuantity());
        assertNotNull(ingredientNew.getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientNew.getMeasure().getId());
    }
}
