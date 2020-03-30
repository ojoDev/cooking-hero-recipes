package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientNewBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.*;
import com.ojodev.cookinghero.recipes.infrastructure.repository.IngredientsRepository;
import com.ojodev.cookinghero.recipes.infrastructure.repository.MeasuresRepository;
import com.ojodev.cookinghero.recipes.infrastructure.repository.ProductsRepository;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsBusinessAddOrReplaceIngredientTest {

    public static final String INVALID_ID = "xxxxx";

    @Autowired
    private IngredientsBusiness ingredientsBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @MockBean
    private RecipesRepository recipesRepository;


    @MockBean
    private ProductsRepository productsRepository;

    @MockBean
    private MeasuresRepository measuresRepository;

    @Test
    public void addOrReplaceIngredientExistentIngredientExistentProduct() {
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

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, LanguageEnumBO.EN);
        recipePO.setIngredients(ingredientPOList);

        IngredientNewBO newIngredient = new IngredientNewBO(RecipesExamples.RECIPE_01_ID, ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, MeasuresExamples.MEASURE_01_ID);

        when(this.productsRepository.findProducts(eq(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR), any(), any(), anyInt(), anyInt())).thenReturn(Arrays.asList(product02));
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(Arrays.asList(measure01));
        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID)).thenReturn(Arrays.asList(recipePO));

        Assertions.assertDoesNotThrow(() -> ingredientsBusiness.addOrReplaceIngredient(newIngredient));

        verify(productsRepository).findProducts(eq(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR), any(), any(), anyInt(), anyInt());
        verify(measuresRepository).findByObjectId(MeasuresExamples.MEASURE_01_ID);
        verify(recipesRepository).findByObjectId(RecipesExamples.RECIPE_01_ID);
        verify(ingredientsRepository).save(any(IngredientPO.class));

    }

    @Test
    public void addOrReplaceIngredientNewIngredientNewProduct() {
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

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, LanguageEnumBO.EN);
        recipePO.setIngredients(ingredientPOList);

        IngredientNewBO newIngredient = new IngredientNewBO(RecipesExamples.RECIPE_01_ID, ProductsExamples.PRODUCT_03_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, MeasuresExamples.MEASURE_01_ID);

        when(this.productsRepository.findProducts(eq(ProductsExamples.PRODUCT_03_NAME_ENGLISH_SINGULAR), any(), any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(Arrays.asList(measure01));
        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID)).thenReturn(Arrays.asList(recipePO));

        Assertions.assertDoesNotThrow(() -> ingredientsBusiness.addOrReplaceIngredient(newIngredient));

        verify(productsRepository).findProducts(eq(ProductsExamples.PRODUCT_03_NAME_ENGLISH_SINGULAR), any(), any(), anyInt(), anyInt());
        verify(measuresRepository).findByObjectId(MeasuresExamples.MEASURE_01_ID);
        verify(recipesRepository).findByObjectId(RecipesExamples.RECIPE_01_ID);
        verify(ingredientsRepository).save(any(IngredientPO.class));

    }

    @Test
    public void addOrReplaceIngredientRecipeNotFound() {
        MeasurePO measure01 = new MeasurePO(MeasuresExamples.MEASURE_01_ID,
                Arrays.asList(new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, ProductsExamples.LANGUAGE_EN),
                        new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, ProductsExamples.LANGUAGE_ES)));

        IngredientNewBO newIngredient = new IngredientNewBO(RecipesExamples.RECIPE_01_ID, ProductsExamples.PRODUCT_03_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, MeasuresExamples.MEASURE_01_ID);

        when(this.productsRepository.findProducts(eq(ProductsExamples.PRODUCT_03_NAME_ENGLISH_SINGULAR), any(), any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(Arrays.asList(measure01));
        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID)).thenReturn(new ArrayList<>());

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> {
                    ingredientsBusiness.addOrReplaceIngredient(newIngredient);
                }, "Need to be throw an exception. Recipe not found.");

        Assert.assertEquals(messages.get("error.notfound.recipe.code"), exception.getCode());
        Assert.assertEquals(messages.get("error.notfound.recipe.desc"), exception.getDescription());

        verify(ingredientsRepository, never()).save(any(IngredientPO.class));
    }


    @Test
    public void addOrReplaceIngredientMeasureNotFound() {
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

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, LanguageEnumBO.EN);
        recipePO.setIngredients(ingredientPOList);

        IngredientNewBO newIngredient = new IngredientNewBO(RecipesExamples.RECIPE_01_ID, ProductsExamples.PRODUCT_03_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, MeasuresExamples.MEASURE_01_ID);

        when(this.productsRepository.findProducts(eq(ProductsExamples.PRODUCT_03_NAME_ENGLISH_SINGULAR), any(), any(), anyInt(), anyInt())).thenReturn(new ArrayList<>());
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(new ArrayList<>());
        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID)).thenReturn(Arrays.asList(recipePO));

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class,
                () -> {
                    ingredientsBusiness.addIngredient(newIngredient);
                }, "Need to be throw an exception. Measure not found.");

        Assert.assertEquals(messages.get("error.notfound.measure.code"), exception.getCode());
        Assert.assertEquals(messages.get("error.notfound.measure.desc"), exception.getDescription());

        verify(ingredientsRepository, never()).save(any(IngredientPO.class));
    }


}
