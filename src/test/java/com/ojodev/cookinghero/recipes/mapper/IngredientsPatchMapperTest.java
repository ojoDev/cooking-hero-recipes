package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.IngredientUpdate;
import com.ojodev.cookinghero.recipes.api.model.MeasureRef;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsPatchMapperTest {

    @Autowired
    private IngredientsPatchMapper ingredientsPatchMapper;

    @Test
    public void patchIngredientComplete() {

        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        IngredientBO originalIngredient = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product01, BigDecimal.valueOf(2), measure01);

        IngredientUpdate patchIngredient = new IngredientUpdate();
        patchIngredient.setProductName(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);
        patchIngredient.setQuantity(IngredientsExamples.INGREDIENT_01_QUANTITY_CHANGED);
        patchIngredient.setMeasure(new MeasureRef(MeasuresExamples.MEASURE_02_ID));

        IngredientNewBO resultIngredient = ingredientsPatchMapper.patch(originalIngredient, patchIngredient);
        assertNotNull(resultIngredient);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, resultIngredient.getId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, resultIngredient.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY_CHANGED, resultIngredient.getQuantity());
        assertEquals(MeasuresExamples.MEASURE_02_ID, resultIngredient.getMeasureId());

    }

    @Test
    public void patchIngredientPartial() {

        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        IngredientBO originalIngredient = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product01, IngredientsExamples.INGREDIENT_01_QUANTITY, measure01);

        IngredientUpdate patchIngredient01 = new IngredientUpdate();
        patchIngredient01.setProductName(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);

        IngredientNewBO resultIngredient01 = ingredientsPatchMapper.patch(originalIngredient, patchIngredient01);
        assertNotNull(resultIngredient01);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, resultIngredient01.getId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, resultIngredient01.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, resultIngredient01.getQuantity());
        assertEquals(MeasuresExamples.MEASURE_01_ID, resultIngredient01.getMeasureId());


        IngredientUpdate patchIngredient02 = new IngredientUpdate();
        patchIngredient02.setQuantity(IngredientsExamples.INGREDIENT_01_QUANTITY_CHANGED);

        IngredientNewBO resultIngredient02 = ingredientsPatchMapper.patch(originalIngredient, patchIngredient02);
        assertNotNull(resultIngredient02);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, resultIngredient02.getId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, resultIngredient02.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY_CHANGED, resultIngredient02.getQuantity());
        assertEquals(MeasuresExamples.MEASURE_01_ID, resultIngredient02.getMeasureId());


        IngredientUpdate patchIngredient03 = new IngredientUpdate();
        patchIngredient03.setMeasure(new MeasureRef(MeasuresExamples.MEASURE_02_ID));

        IngredientNewBO resultIngredient03 = ingredientsPatchMapper.patch(originalIngredient, patchIngredient03);
        assertNotNull(resultIngredient03);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, resultIngredient03.getId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, resultIngredient03.getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, resultIngredient03.getQuantity());
        assertEquals(MeasuresExamples.MEASURE_02_ID, resultIngredient03.getMeasureId());

    }

    @Test
    public void patchIngredientPartialWithNull() {

        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        IngredientBO originalIngredient = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product01, BigDecimal.valueOf(2), measure01);

        IngredientUpdate patchIngredient = new IngredientUpdate();
        patchIngredient.setProductName(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED);
        patchIngredient.setMeasure(null);
        patchIngredient.setQuantity(null);

        IngredientNewBO resultIngredient = ingredientsPatchMapper.patch(originalIngredient, patchIngredient);
        assertNotNull(resultIngredient);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, resultIngredient.getId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, resultIngredient.getProductName());
        assertNull(resultIngredient.getQuantity());
        assertNull(resultIngredient.getMeasureId());
    }
}
