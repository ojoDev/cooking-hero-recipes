package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.api.model.IngredientNew;
import com.ojodev.cookinghero.recipes.api.model.MeasureRef;
import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.*;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IngredientsApiControllerPatchTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private IngredientsBusiness ingredientsBusiness;


    @Test
    public void patchIngredientAllFields() throws Exception {
        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        String ingredientId = RecipesExamples.RECIPE_01_ID + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR;
        IngredientBO ingredientBO = new IngredientBO(ingredientId, product01, IngredientsExamples.INGREDIENT_01_QUANTITY, measure01);

        when(this.ingredientsBusiness.getIngredient(RecipesExamples.RECIPE_01_ID, ingredientId)).thenReturn(Optional.of(ingredientBO));

        this.mvc.perform(patch("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_01_ID, ingredientId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(fileUtils.fileAsJsonString(FileNameEnum.INGREDIENT_PATCH_COMPLETE)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<IngredientNewBO> argumentIngredientNew = ArgumentCaptor.forClass(IngredientNewBO.class);
        verify(ingredientsBusiness).addOrReplaceIngredient(argumentIngredientNew.capture());
        assertEquals("If name is changed, old id does not change", ingredientId, argumentIngredientNew.getValue().getId());
        assertEquals(RecipesExamples.RECIPE_01_ID, argumentIngredientNew.getValue().getRecipeId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, argumentIngredientNew.getValue().getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY_CHANGED, argumentIngredientNew.getValue().getQuantity());
        assertEquals(MeasuresExamples.MEASURE_02_ID, argumentIngredientNew.getValue().getMeasureId());

    }

    @Test
    public void patchIngredientPartialFields() throws Exception {

        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        String ingredientId = RecipesExamples.RECIPE_01_ID + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR;
        IngredientBO ingredientBO = new IngredientBO(ingredientId, product01, IngredientsExamples.INGREDIENT_01_QUANTITY, measure01);

        when(this.ingredientsBusiness.getIngredient(RecipesExamples.RECIPE_01_ID, ingredientId)).thenReturn(Optional.of(ingredientBO));

        this.mvc.perform(patch("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_01_ID, ingredientId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(fileUtils.fileAsJsonString(FileNameEnum.INGREDIENT_PATCH_PARTIAL)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<IngredientNewBO> argumentIngredientNew = ArgumentCaptor.forClass(IngredientNewBO.class);
        verify(ingredientsBusiness).addOrReplaceIngredient(argumentIngredientNew.capture());
        assertEquals(ingredientId, argumentIngredientNew.getValue().getId());
        assertEquals(RecipesExamples.RECIPE_01_ID, argumentIngredientNew.getValue().getRecipeId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, argumentIngredientNew.getValue().getProductName());
        assertEquals(IngredientsExamples.INGREDIENT_01_QUANTITY, argumentIngredientNew.getValue().getQuantity());
        assertEquals(MeasuresExamples.MEASURE_01_ID, argumentIngredientNew.getValue().getMeasureId());

    }

    @Test
    public void patchIngredientPartialNullValues() throws Exception {

        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        String ingredientId = RecipesExamples.RECIPE_01_ID + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR;
        IngredientBO ingredientBO = new IngredientBO(ingredientId, product01, IngredientsExamples.INGREDIENT_01_QUANTITY, measure01);

        when(this.ingredientsBusiness.getIngredient(RecipesExamples.RECIPE_01_ID, ingredientId)).thenReturn(Optional.of(ingredientBO));

        this.mvc.perform(patch("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_01_ID, ingredientId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(fileUtils.fileAsJsonString(FileNameEnum.INGREDIENT_PATCH_PARTIAL_NULL)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<IngredientNewBO> argumentIngredientNew = ArgumentCaptor.forClass(IngredientNewBO.class);
        verify(ingredientsBusiness).addOrReplaceIngredient(argumentIngredientNew.capture());
        assertEquals(ingredientId, argumentIngredientNew.getValue().getId());
        assertEquals(RecipesExamples.RECIPE_01_ID, argumentIngredientNew.getValue().getRecipeId());
        assertEquals(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR_CHANGED, argumentIngredientNew.getValue().getProductName());
        assertNull(argumentIngredientNew.getValue().getQuantity());
        assertNull(argumentIngredientNew.getValue().getMeasureId());

    }

    @Test
    public void patchIngredientRecipeNotFound() throws Exception {

        IngredientNew ingredientNew = new IngredientNew(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, new MeasureRef(MeasuresExamples.MEASURE_01_ID));
        String ingredientId = RecipesExamples.RECIPE_01_ID + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR;

        doThrow(new NotFoundException(messages.get("error.notfound.recipe.code"), messages.get("error.notfound.recipe.desc"))).when(ingredientsBusiness).getIngredient(any(), any());

        this.mvc.perform(patch("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_01_ID, ingredientId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(ingredientNew)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.recipe.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.recipe.desc"))));

    }

    @Test
    public void patchIngredientIngredientNotFound() throws Exception {

        IngredientNew ingredientNew = new IngredientNew(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, IngredientsExamples.INGREDIENT_01_QUANTITY, new MeasureRef(MeasuresExamples.MEASURE_01_ID));
        String ingredientId = RecipesExamples.RECIPE_01_ID + "-" + ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR;

        doThrow(new NotFoundException(messages.get("error.notfound.ingredient.code"), messages.get("error.notfound.ingredient.desc"))).when(ingredientsBusiness).addOrReplaceIngredient(any());

        this.mvc.perform(patch("/recipes/{recipe-id}/ingredients/{ingredient-id}", RecipesExamples.RECIPE_01_ID, ingredientId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtils.asJsonString(ingredientNew)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.ingredient.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.ingredient.desc"))));

    }


}