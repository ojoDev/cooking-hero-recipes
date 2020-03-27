package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientUpdateTest {

    @Test
    public void testNullOptionals() {
        IngredientUpdate ingredientUpdate = new IngredientUpdate();
        ingredientUpdate.setProductName(null);
        ingredientUpdate.setQuantity(null);
        ingredientUpdate.setMeasure(null);

        assertNotNull(ingredientUpdate);
        assertNull(ingredientUpdate.getProductName());
        assertNull(ingredientUpdate.getQuantity());
        assertNull(ingredientUpdate.getMeasure());
        assertNull(ingredientUpdate.getProductNameOpt());
        assertNull(ingredientUpdate.getQuantityOpt());
        assertNull(ingredientUpdate.getMeasureOpt());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(ProductUpdate.class);
    }


}
