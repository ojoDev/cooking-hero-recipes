package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.infrastructure.po.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class IngredientsRespositoryTest {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private MeasuresRepository measuresRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private RecipesRepository recipesRepository;


    @Test
    public void addIngredient() {
        String objectId = "test-recipe01-salt2";
        ProductPO product = productsRepository.findByObjectId("salt").get(0);
        MeasurePO measure = measuresRepository.findByObjectId("tablespoon").get(0);
        RecipePO recipe = recipesRepository.findByObjectId("test-recipe01").get(0);

        IngredientPO newIngredient = new IngredientPO(objectId, product, 2, measure);
        newIngredient.setRecipe(recipe);
        ingredientsRepository.save(newIngredient);
        IngredientPO ingredient = ingredientsRepository.findByObjectId(objectId).get(0);
        Assert.assertNotNull(ingredient);
    }

    @Test
    public void getIngredient() {
        String objectId = "test-recipe01-salt";
        IngredientPO ingredient = ingredientsRepository.findByObjectId(objectId).get(0);
        Assert.assertNotNull(ingredient);
    }

}
