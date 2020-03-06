package com.ojodev.cookinghero.recipes.api.controller;


import com.ojodev.cookinghero.recipes.business.IngredientsBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.data.ProductsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IngredientsApiControllerGetIngredientsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private IngredientsBusiness ingredientsBusiness;


    @Test
    public void getIngredients() throws Exception {
        ProductBO product01 = new ProductBO(ProductsExamples.PRODUCT_01_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.APPROVED_BY_ADMIN);
        MeasureBO measure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        ProductBO product02 = new ProductBO(ProductsExamples.PRODUCT_02_ID,
                new DescriptiveNameBO(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR, ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN), ProductStatusEnumBO.CREATED_BY_USER);
        List<IngredientBO> ingredientBOList = Arrays.asList(new IngredientBO(IngredientsExamples.INGREDIENT_01_ID, product01, BigDecimal.valueOf(2), measure01),
                new IngredientBO(IngredientsExamples.INGREDIENT_02_ID, product02));

        when(this.ingredientsBusiness.getIngredients(RecipesExamples.RECIPE_ID_01)).thenReturn(ingredientBOList);

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients", RecipesExamples.RECIPE_ID_01)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$.[0].id", is(IngredientsExamples.INGREDIENT_01_ID)))
                .andExpect(jsonPath("$.[0].product.id", is(ProductsExamples.PRODUCT_01_ID)))
                .andExpect(jsonPath("$.[0].product.name.singular", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.[0].product.name.plural", is(ProductsExamples.PRODUCT_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.[0].product.status", is(ProductStatusEnumBO.APPROVED_BY_ADMIN.toString())))
                .andExpect(jsonPath("$.[0].quantity", is(2)))
                .andExpect(jsonPath("$.[0].measure.id", is(MeasuresExamples.MEASURE_01_ID)))
                .andExpect(jsonPath("$.[0].measure.name.singular", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.[0].measure.name.plural", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.[1].id", is(IngredientsExamples.INGREDIENT_02_ID)))
                .andExpect(jsonPath("$.[1].product.id", is(ProductsExamples.PRODUCT_02_ID)))
                .andExpect(jsonPath("$.[1].product.name.singular", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$.[1].product.name.plural", is(ProductsExamples.PRODUCT_02_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$.[1].product.status", is(ProductStatusEnumBO.CREATED_BY_USER.toString())))
                .andExpect(jsonPath("$.[1].quantity").doesNotExist())
                .andExpect(jsonPath("$.[1].measure").doesNotExist());
    }


    @Test
    public void getIngredientsNoIngredients() throws Exception {

        when(this.ingredientsBusiness.getIngredients(RecipesExamples.RECIPE_ID_02)).thenReturn(new ArrayList<>());

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients", RecipesExamples.RECIPE_ID_02)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(0)));
    }

    @Test
    public void getProductByIdNotFound() throws Exception {

        when(this.ingredientsBusiness.getIngredients(RecipesExamples.RECIPE_ID_02)).thenThrow(new NotFoundException(messages.get("error.notfound.recipe.code"), messages.get("error.notfound.recipe.desc")));

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients", RecipesExamples.RECIPE_ID_02)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.recipe.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.recipe.desc"))));
    }

    @Test
    public void getProductOutOfMemoryException() throws Exception {

        when(this.ingredientsBusiness.getIngredients(RecipesExamples.RECIPE_ID_01)).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/recipes/{recipe-id}/ingredients", RecipesExamples.RECIPE_ID_01)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
    }


}
