package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
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
public class IngredientBOTest {

    @Test
    public void constructorTwoFields() {
        ProductBO product = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        IngredientBO ingredientBO = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product);
        assertNotNull(ingredientBO);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientBO.getId());
        assertNotNull(ingredientBO.getProduct());
        assertNotNull(ingredientBO.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientBO.getProduct().getId());
        assertNotNull(ingredientBO.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientBO.getProduct().getName().getSingular());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientBO.getProduct().getStatus());
        assertNull(ingredientBO.getQuantity());
        assertNull(ingredientBO.getMeasure());
    }

    @Test
    public void constructorThreeFields() {
        ProductBO product = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        IngredientBO ingredientBO = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product, IngredientsExamples.INGREDIENT_01_QUANTITY);
        assertNotNull(ingredientBO);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientBO.getId());
        assertNotNull(ingredientBO.getProduct());
        assertNotNull(ingredientBO.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientBO.getProduct().getId());
        assertNotNull(ingredientBO.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientBO.getProduct().getName().getSingular());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientBO.getProduct().getStatus());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientBO.getQuantity());
        assertNull(ingredientBO.getMeasure());
    }

    @Test
    public void constructorFourFields() {
        ProductBO product = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        IngredientBO ingredientBO = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product, IngredientsExamples.INGREDIENT_01_QUANTITY, measure);

        assertNotNull(ingredientBO);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientBO.getId());
        assertNotNull(ingredientBO.getProduct());
        assertNotNull(ingredientBO.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientBO.getProduct().getId());
        assertNotNull(ingredientBO.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientBO.getProduct().getName().getSingular());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientBO.getProduct().getStatus());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientBO.getQuantity());
        assertNotNull(ingredientBO.getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientBO.getMeasure().getId());
        assertNotNull(ingredientBO.getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, ingredientBO.getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ingredientBO.getMeasure().getName().getPlural());
    }

    @Test
    public void getterAndSetter() {
        ProductBO product = new ProductBO(ProductsExamples.PRODUCT_01_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        ProductBO productChanged = new ProductBO(ProductsExamples.PRODUCT_02_ID, new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        MeasureBO measure = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureBO measureChanged = new MeasureBO(MeasuresExamples.MEASURE_02_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        IngredientBO ingredientBO = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product, IngredientsExamples.INGREDIENT_01_QUANTITY, measure);

        assertNotNull(ingredientBO);

        ingredientBO.setId(IngredientsExamples.INGREDIENT_02_ID);
        ingredientBO.setProduct(productChanged);
        ingredientBO.setQuantity(IngredientsExamples.INGREDIENT_01_QUANTITY_CHANGED);
        ingredientBO.setMeasure(measureChanged);

        assertEquals(IngredientsExamples.INGREDIENT_02_ID, ingredientBO.getId());
        assertNotNull(ingredientBO.getProduct());
        assertEquals(ProductsExamples.PRODUCT_02_ID, ingredientBO.getProduct().getId());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY_CHANGED, ingredientBO.getQuantity());
        assertNotNull(ingredientBO.getMeasure());
        assertEquals(MeasuresExamples.MEASURE_02_ID, ingredientBO.getMeasure().getId());


    }

}
