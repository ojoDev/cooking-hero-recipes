package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.*;
import com.ojodev.cookinghero.recipes.infrastructure.repository.IngredientsRepository;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsBusinessDeleteIngredientTest {

    public static final String INVALID_ID = "xxxxx";

    @Autowired
    private IngredientsBusiness ingredientsBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @MockBean
    private RecipesRepository recipesRepository;


    @Test
    public void deleteIngredient() {
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

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_ID_01, RecipesExamples.RECIPE_NAME_01, RecipesExamples.RECIPE_DESCRIPTION_01, LanguageEnumBO.EN);
        recipePO.setIngredients(ingredientPOList);

        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01)).thenReturn(Arrays.asList(recipePO));

        Assertions.assertDoesNotThrow(() -> ingredientsBusiness.deleteIngredient(RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID));
        verify(ingredientsRepository).deleteByObjectId(IngredientsExamples.INGREDIENT_01_ID);

    }

    @Test
    public void deleteIngredientRecipeNotFound() {
        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01)).thenReturn(new ArrayList<>());

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            ingredientsBusiness.deleteIngredient(RecipesExamples.RECIPE_ID_01, IngredientsExamples.INGREDIENT_01_ID);
        });
        assertNotNull(exception);
        Assert.assertEquals(messages.get("error.notfound.recipe.code"), exception.getCode());
        Assert.assertEquals(messages.get("error.notfound.recipe.desc"), exception.getDescription());

        verify(ingredientsRepository, never()).deleteByObjectId(IngredientsExamples.INGREDIENT_01_ID);

    }

    @Test
    public void deleteIngredientIngredientNotFoundInRecipe() {
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

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_ID_01, RecipesExamples.RECIPE_NAME_01, RecipesExamples.RECIPE_DESCRIPTION_01, LanguageEnumBO.EN);
        recipePO.setIngredients(ingredientPOList);

        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01)).thenReturn(Arrays.asList(recipePO));

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            ingredientsBusiness.deleteIngredient(RecipesExamples.RECIPE_ID_01, INVALID_ID);
        });
        assertNotNull(exception);
        Assert.assertEquals(messages.get("error.notfound.ingredient.code"), exception.getCode());
        Assert.assertEquals(messages.get("error.notfound.ingredient.desc"), exception.getDescription());

        verify(ingredientsRepository, never()).deleteByObjectId(IngredientsExamples.INGREDIENT_01_ID);
    }



    @Test
    public void deleteIngredientRecipeWithoutIngredients() {

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_ID_01, RecipesExamples.RECIPE_NAME_01, RecipesExamples.RECIPE_DESCRIPTION_01, LanguageEnumBO.EN);

        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01)).thenReturn(Arrays.asList(recipePO));

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            ingredientsBusiness.deleteIngredient(RecipesExamples.RECIPE_ID_01, INVALID_ID);
        });
        assertNotNull(exception);
        Assert.assertEquals(messages.get("error.notfound.ingredient.code"), exception.getCode());
        Assert.assertEquals(messages.get("error.notfound.ingredient.desc"), exception.getDescription());

        verify(ingredientsRepository, never()).deleteByObjectId(IngredientsExamples.INGREDIENT_01_ID);
    }


}
