package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.IngredientBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsBusinessGetIngredientsTest {

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
    public void getIngredients() throws NotFoundException {
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
        when(this.ingredientsRepository.findByRecipeId(RecipesExamples.RECIPE_ID_01)).thenReturn(ingredientPOList);

        List<IngredientBO> ingredientBOList = ingredientsBusiness.getIngredients(RecipesExamples.RECIPE_ID_01);
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

    @Test
    public void getIngredientsRecipeNoDefaultLanguage() throws NotFoundException {
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

        RecipePO recipePO = new RecipePO(RecipesExamples.RECIPE_ID_01, RecipesExamples.RECIPE_NAME_01, RecipesExamples.RECIPE_DESCRIPTION_01, LanguageEnumBO.ES);
        recipePO.setIngredients(ingredientPOList);

        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01)).thenReturn(Arrays.asList(recipePO));
        when(this.ingredientsRepository.findByRecipeId(RecipesExamples.RECIPE_ID_01)).thenReturn(ingredientPOList);

        List<IngredientBO> ingredientBOList = ingredientsBusiness.getIngredients(RecipesExamples.RECIPE_ID_01);
        assertNotNull(ingredientBOList);
        assertEquals(2, ingredientBOList.size());
        assertEquals(IngredientsExamples.INGREDIENT_01_ID, ingredientBOList.get(0).getId());
        assertNotNull(ingredientBOList.get(0).getProduct());
        assertEquals(ProductsExamples.PRODUCT_01_ID, ingredientBOList.get(0).getProduct().getId());
        assertNotNull(ingredientBOList.get(0).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_SINGULAR, ingredientBOList.get(0).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_SPANISH_PLURAL, ingredientBOList.get(0).getProduct().getName().getPlural());
        assertEquals(LanguageEnumBO.ES, ingredientBOList.get(0).getProduct().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.APPROVED_BY_ADMIN, ingredientBOList.get(0).getProduct().getStatus());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, ingredientBOList.get(0).getQuantity());
        assertNotNull(ingredientBOList.get(0).getMeasure());
        assertEquals(MeasuresExamples.MEASURE_01_ID, ingredientBOList.get(0).getMeasure().getId());
        assertNotNull(ingredientBOList.get(0).getMeasure().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, ingredientBOList.get(0).getMeasure().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, ingredientBOList.get(0).getMeasure().getName().getPlural());
        assertEquals(LanguageEnumBO.ES, ingredientBOList.get(0).getMeasure().getName().getLanguage());

        assertEquals(IngredientsExamples.INGREDIENT_02_ID, ingredientBOList.get(1).getId());
        assertNotNull(ingredientBOList.get(1).getProduct());
        assertEquals(ProductsExamples.PRODUCT_02_ID, ingredientBOList.get(1).getProduct().getId());
        assertNotNull(ingredientBOList.get(1).getProduct().getName());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_SPANISH_SINGULAR, ingredientBOList.get(1).getProduct().getName().getSingular());
        assertEquals(ProductsExamples.PRODUCT_02_NAME_SPANISH_PLURAL, ingredientBOList.get(1).getProduct().getName().getPlural());
        assertEquals(LanguageEnumBO.ES, ingredientBOList.get(1).getProduct().getName().getLanguage());
        assertEquals(ProductStatusEnumBO.CREATED_BY_USER, ingredientBOList.get(1).getProduct().getStatus());
        assertNull(ingredientBOList.get(1).getQuantity());
        assertNull(ingredientBOList.get(1).getMeasure());

    }

    @Test
    public void getIngredientsRecipeNotFound() {
        when(this.recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01)).thenReturn(new ArrayList<>());

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            ingredientsBusiness.getIngredients(RecipesExamples.RECIPE_ID_01);
        });
        assertNotNull(exception);
        Assert.assertEquals(messages.get("error.notfound.recipe.code"), exception.getCode());
        Assert.assertEquals(messages.get("error.notfound.recipe.desc"), exception.getDescription());
    }
}
