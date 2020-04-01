package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
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

import java.util.List;

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
        RecipePO newRecipe = new RecipePO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME);
        newRecipe.setDescription(RecipesExamples.RECIPE_01_DESCRIPTION);
        newRecipe.setLanguage(LanguageEnumBO.EN.toString());

        recipesRepository.save(newRecipe);
        RecipePO recipe = recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID).get(0);
        Assert.assertNotNull(recipe);
    }

    @Test
    @Ignore
    public void existsByObjectId() {
        boolean exists = recipesRepository.existsByObjectId(RecipesExamples.RECIPE_01_ID);
        Assert.assertTrue(exists);
    }


    @Test
    @Ignore
    public void getByObjectId() {
        List<RecipePO> recipeList = recipesRepository.findByObjectId(RecipesExamples.RECIPE_01_ID);
        Assert.assertNotNull(recipeList);
    }

}
