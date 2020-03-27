package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientPOTest {

    @Test
    public void constructorNoFields() {
        IngredientPO ingredientPO = new IngredientPO();

        assertNotNull(ingredientPO);
        assertNull(ingredientPO.getObjectId());
        assertNull(ingredientPO.getProduct());
        assertNull(ingredientPO.getQuantity());
        assertNull(ingredientPO.getMeasure());
    }


    @Test
    public void constructorTwoFields() {
        DescriptiveNamePO descriptiveName = new DescriptiveNamePO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, DescriptiveNamesExamples.LANGUAGE_ENGLISH);
        ProductPO product = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(descriptiveName), ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());

        IngredientPO ingredientPO = new IngredientPO(IngredientsExamples.INGREDIENT_01_ID, product);

        assertNotNull(ingredientPO);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientPO.getObjectId());
        assertNotNull(ingredientPO.getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientPO.getProduct().getObjectId());
        assertNull(ingredientPO.getQuantity());
        assertNull(ingredientPO.getMeasure());
    }

    @Test
    public void constructorAllFields() {
        DescriptiveNamePO descriptiveNameProduct = new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, DescriptiveNamesExamples.LANGUAGE_ENGLISH);
        ProductPO product = new ProductPO(ProductsExamples.PRODUCT_01_ID, Arrays.asList(descriptiveNameProduct), ProductStatusEnumBO.APPROVED_BY_ADMIN.toString());
        DescriptiveNamePO descriptiveNameMeasure = new DescriptiveNamePO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, DescriptiveNamesExamples.LANGUAGE_ENGLISH);
        MeasurePO measure = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(descriptiveNameMeasure));

        IngredientPO ingredientPO = new IngredientPO(IngredientsExamples.INGREDIENT_01_ID, product, IngredientsExamples.INGREDIENT_01_QUANTITY, measure);

        assertNotNull(ingredientPO);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientPO.getObjectId());
        assertNotNull(ingredientPO.getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientPO.getProduct().getObjectId());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientPO.getQuantity());
        assertNotNull(ingredientPO.getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientPO.getMeasure().getObjectId());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(IngredientPO.class);
    }
}