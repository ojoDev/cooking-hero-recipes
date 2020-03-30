package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.infrastructure.po.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class IngredientsRepositoryTest {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private MeasuresRepository measuresRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private RecipesRepository recipesRepository;


    @Test
    @Ignore
    public void addIngredient() {

        ProductPO product = productsRepository.findByObjectId("salt").get(0);
        MeasurePO measure = measuresRepository.findByObjectId("tablespoon").get(0);
        RecipePO recipe = recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID).get(0);

        IngredientPO newIngredient = new IngredientPO(IngredientsExamples.INGREDIENT_01_ID, product, BigDecimal.valueOf(0.5), measure);
        newIngredient.setRecipe(recipe);
        ingredientsRepository.save(newIngredient);
        IngredientPO ingredient = ingredientsRepository.findByObjectId(IngredientsExamples.INGREDIENT_01_ID).get(0);
        Assert.assertNotNull(ingredient);
    }

    @Test
    @Ignore
    public void getIngredientByObjectId() {
        IngredientPO ingredient = ingredientsRepository.findByObjectId(IngredientsExamples.INGREDIENT_01_ID).get(0);
        Assert.assertNotNull(ingredient);
        Assert.assertNotNull(ingredient.getProduct());
        Assert.assertNotNull(ingredient.getProduct().getNames());
        Assert.assertNotNull(ingredient.getProduct().getNames().get(0));
        Assert.assertNotNull(ingredient.getProduct().getNames().get(0).getSingular());
        Assert.assertNotNull(ingredient.getProduct().getNames().get(0).getPlural());
        Assert.assertNotNull(ingredient.getQuantity());
        Assert.assertNotNull(ingredient.getMeasure());
        Assert.assertNotNull(ingredient.getMeasure().getNames());
        Assert.assertNotNull(ingredient.getMeasure().getNames().get(0).getSingular());
        Assert.assertNotNull(ingredient.getMeasure().getNames().get(0).getPlural());
    }

    @Test
    @Ignore
    public void getIngredientByRecipeId() {
        IngredientPO ingredient = ingredientsRepository.findByRecipeId(RecipesExamples.RECIPE_01_ID).get(0);
        Assert.assertNotNull(ingredient);
        Assert.assertNotNull(ingredient.getProduct());
        Assert.assertNotNull(ingredient.getProduct().getNames());
        Assert.assertNotNull(ingredient.getProduct().getNames().get(0));
        Assert.assertNotNull(ingredient.getProduct().getNames().get(0).getSingular());
        Assert.assertNotNull(ingredient.getProduct().getNames().get(0).getPlural());
        Assert.assertNotNull(ingredient.getQuantity());
        Assert.assertNotNull(ingredient.getMeasure());
        Assert.assertNotNull(ingredient.getMeasure().getNames());
        Assert.assertNotNull(ingredient.getMeasure().getNames().get(0).getSingular());
        Assert.assertNotNull(ingredient.getMeasure().getNames().get(0).getPlural());
    }

    @Test
    @Ignore
    public void updateIngredient() {
        IngredientPO ingredient = ingredientsRepository.findByRecipeId(RecipesExamples.RECIPE_01_ID).get(0);
        Assert.assertNotNull(ingredient);
        BigDecimal quantity = ingredient.getQuantity();
        BigDecimal quantityPlusOne = quantity.add(new BigDecimal(1.0));

        ingredient.setQuantity(quantityPlusOne);
        ingredientsRepository.save(ingredient);

        IngredientPO ingredientModified = ingredientsRepository.findByRecipeId(RecipesExamples.RECIPE_01_ID).get(0);
        Assert.assertNotNull(ingredientModified);
        Assert.assertEquals(quantityPlusOne, ingredientModified.getQuantity());

    }

}
