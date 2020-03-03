package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class RecipesRepositoryTest {


    @Autowired
    private RecipesRepository recipesRepository;


    @Test
    @Ignore
    public void addBasicRecipe() {
        RecipePO newRecipe = new RecipePO(RecipesExamples.RECIPE_ID_01, RecipesExamples.RECIPE_NAME_01, RecipesExamples.RECIPE_DESCRIPTION_01);
        recipesRepository.save(newRecipe);
        RecipePO recipe = recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01).get(0);
        Assert.assertNotNull(recipe);
    }

    @Test
    @Ignore
    public void existsByObjectId() {
        boolean exists = recipesRepository.existsByObjectId(RecipesExamples.RECIPE_ID_01);
        Assert.assertTrue(exists);
    }


}
