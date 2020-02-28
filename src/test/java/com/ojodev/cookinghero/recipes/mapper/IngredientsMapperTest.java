package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.domain.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsMapperTest {

    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Test
    public void toIngredient() {
        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        IngredientBO ingredientBO = new IngredientBO(product01, BigDecimal.valueOf(2), measure01);

        Ingredient ingredient = ingredientsMapper.toIngredient(ingredientBO);

        assertNotNull(ingredient);

        assertNotNull(ingredient.getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredient.getProduct().getId());
        assertNotNull(ingredient.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredient.getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ingredient.getProduct().getName().getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredient.getProduct().getStatus());
        assertEquals(BigDecimal.valueOf(2), ingredient.getQuantity());
        assertNotNull(ingredient.getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredient.getMeasure().getId());
        assertNotNull(MeasuresExamples.MEASURE_01_ID, ingredient.getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, ingredient.getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ingredient.getMeasure().getName().getPlural());
    }

    @Test
    public void toIngredientsList() {
        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        ProductBO product02 = new ProductBO(ProductsExamples.PRODUCT_02_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        List<IngredientBO> ingredientBOList = Arrays.asList(
                new IngredientBO(product01, BigDecimal.valueOf(2), measure01),
                new IngredientBO(product02));

        List<Ingredient> ingredientList = ingredientsMapper.toIngredientList(ingredientBOList);

        assertNotNull(ingredientList);
        assertEquals(2, ingredientList.size());
        assertNotNull(ingredientList.get(0).getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientList.get(0).getProduct().getId());
        assertNotNull(ingredientList.get(0).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientList.get(0).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ingredientList.get(0).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientList.get(0).getProduct().getStatus());
        assertEquals(BigDecimal.valueOf(2), ingredientList.get(0).getQuantity());
        assertNotNull(ingredientList.get(0).getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientList.get(0).getMeasure().getId());
        assertNotNull(MeasuresExamples.MEASURE_01_ID, ingredientList.get(0).getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, ingredientList.get(0).getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ingredientList.get(0).getMeasure().getName().getPlural());

        assertEquals(ProductsExamples.PRODUCT_02_ID, ingredientList.get(1).getProduct().getId());
        assertNotNull(ingredientList.get(1).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ingredientList.get(1).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, ingredientList.get(1).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientList.get(1).getProduct().getStatus());
        assertNull(ingredientList.get(1).getQuantity());
        assertNull(ingredientList.get(0).getMeasure());

    }

}
