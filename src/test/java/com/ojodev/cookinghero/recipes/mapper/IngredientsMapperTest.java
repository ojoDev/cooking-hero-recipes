package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Ingredient;
import com.ojodev.cookinghero.recipes.api.model.ProductStatusEnum;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        IngredientBO ingredientBO = new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product01, BigDecimal.valueOf(2), measure01);

        Ingredient ingredient = ingredientsMapper.toIngredient(ingredientBO);

        assertNotNull(ingredient);

        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredient.getProduct().getId());
        assertNotNull(ingredient.getProduct());
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredient.getId());
        assertNotNull(ingredient.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredient.getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ingredient.getProduct().getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, ingredient.getProduct().getStatus());
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
                new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product01, BigDecimal.valueOf(2), measure01),
                new IngredientBO(IngredientsExamples.INGREDIENT_02_ID, product02));

        List<Ingredient> ingredientList = ingredientsMapper.toIngredientList(ingredientBOList);

        assertNotNull(ingredientList);
        assertEquals(2, ingredientList.size());
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientList.get(0).getId());
        assertNotNull(ingredientList.get(0).getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientList.get(0).getProduct().getId());
        assertNotNull(ingredientList.get(0).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientList.get(0).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ingredientList.get(0).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, ingredientList.get(0).getProduct().getStatus());
        assertEquals(BigDecimal.valueOf(2), ingredientList.get(0).getQuantity());
        assertNotNull(ingredientList.get(0).getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientList.get(0).getMeasure().getId());
        assertNotNull(MeasuresExamples.MEASURE_01_ID, ingredientList.get(0).getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, ingredientList.get(0).getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ingredientList.get(0).getMeasure().getName().getPlural());

        assertEquals(IngredientsExamples.INGREDIENT_02_ID, ingredientList.get(1).getId());
        assertEquals(ProductsExamples.PRODUCT_02_ID, ingredientList.get(1).getProduct().getId());
        assertNotNull(ingredientList.get(1).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ingredientList.get(1).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, ingredientList.get(1).getProduct().getName().getPlural());
        assertEquals(ProductStatusEnum.APPROVED_BY_ADMIN, ingredientList.get(1).getProduct().getStatus());
        assertNull(ingredientList.get(1).getQuantity());
        assertNull(ingredientList.get(1).getMeasure());

    }

    @Test
    public void toIngredientBO() {
        ProductPO product01 = new ProductPO(ProductsExamples.PRODUCT_01_ID,
                Arrays.asList(new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                        new DescriptiveNamePO(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)),
                ProductsExamples.PRODUCT_STATUS_APPROVED_BY_ADMIN);
        MeasurePO measure01 = new MeasurePO(MeasuresExamples.MEASURE_01_ID,
                Arrays.asList(new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                        new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)));

        IngredientPO ingredientPO01 = new IngredientPO(IngredientsExamples.INGREDIENT_01_ID, product01, IngredientsExamples.INGREDIENT_01_QUANTITY, measure01);

        IngredientBO ingredientBO = ingredientsMapper.toIngredientBO(ingredientPO01, LanguageEnumBO.EN);
        assertNotNull(ingredientBO);
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientBO.getId());
        assertNotNull(ingredientBO.getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientBO.getProduct().getId());
        assertNotNull(ingredientBO.getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientBO.getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ingredientBO.getProduct().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, ingredientBO.getProduct().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientBO.getProduct().getStatus());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientBO.getQuantity());
        assertNotNull(ingredientBO.getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientBO.getMeasure().getId());
        assertNotNull(ingredientBO.getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, ingredientBO.getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ingredientBO.getMeasure().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, ingredientBO.getMeasure().getName().getLanguage());
    }

    @Test
    public void toIngredientBOList() {
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

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_ID_01, RecipesExamples.RECIPE_NAME_01, RecipesExamples.RECIPE_DESCRIPTION_01);
        recipePO.setLanguage(RecipesExamples.LANGUAGE_EN);
        recipePO.setIngredients(ingredientPOList);


        List<IngredientBO> ingredientBOList = ingredientsMapper.toIngredientBOList(ingredientPOList, LanguageEnumBO.EN);
        assertNotNull(ingredientBOList);
        assertEquals(2, ingredientBOList.size());
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientBOList.get(0).getId());
        assertNotNull(ingredientBOList.get(0).getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientBOList.get(0).getProduct().getId());
        assertNotNull(ingredientBOList.get(0).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ingredientBOList.get(0).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, ingredientBOList.get(0).getProduct().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, ingredientBOList.get(0).getProduct().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientBOList.get(0).getProduct().getStatus());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientBOList.get(0).getQuantity());
        assertNotNull(ingredientBOList.get(0).getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientBOList.get(0).getMeasure().getId());
        assertNotNull(ingredientBOList.get(0).getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, ingredientBOList.get(0).getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ingredientBOList.get(0).getMeasure().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, ingredientBOList.get(0).getMeasure().getName().getLanguage());

        assertEquals(IngredientsExamples.INGREDIENT_02_ID, ingredientBOList.get(1).getId());
        assertNotNull(ingredientBOList.get(1).getProduct());
        assertEquals(ProductsExamples.PRODUCT_02_ID, ingredientBOList.get(1).getProduct().getId());
        assertNotNull(ingredientBOList.get(1).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ingredientBOList.get(1).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, ingredientBOList.get(1).getProduct().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, ingredientBOList.get(1).getProduct().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.CREATED_BY_USER, ingredientBOList.get(1).getProduct().getStatus());
        assertNull(ingredientBOList.get(1).getQuantity());
        assertNull(ingredientBOList.get(1).getMeasure());
    }



}
