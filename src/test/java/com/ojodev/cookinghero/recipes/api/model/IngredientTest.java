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
public class IngredientTest extends AbstractJavaBeanTest<Ingredient> {

    @Override
    protected Ingredient getBeanInstance() {
        return new Ingredient();
    }

    @Test
    public void constructorAllFields() {
        Product product = new Product(ProductsExamples.PRODUCT_01_ID, new DescriptiveName(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL), ProductStatusEnum.APPROVED_BY_ADMIN);
        Measure measure = new Measure(MeasuresExamples.MEASURE_01_ID, new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL));
        Ingredient ingredient = new Ingredient(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, product, IngredientsExamples.INGREDIENT_01_QUANTITY, measure);

        assertNotNull(ingredient);
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredient.getId());
        assertNotNull(ingredient.getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredient.getProduct().getId());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredient.getQuantity());
        assertNotNull(ingredient.getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredient.getMeasure().getId());

    }
}
