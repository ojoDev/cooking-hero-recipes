package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipePOTest {

    @Test
    public void testCustomConstructor() {
        RecipePO recipe = new RecipePO(new ObjectId(RecipesExamples.RECIPE_ID_01),
                RecipesExamples.RECIPE_NAME_01,
                RecipesExamples.RECIPE_DESCRIPTION_01,
                Arrays.asList(RecipesExamples.RECIPE_01_CUISINE_TYPE_01, RecipesExamples.RECIPE_01_CUISINE_TYPE_02),
                RecipesExamples.RECIPE_PREPARATION_TIME_01, RecipesExamples.RECIPE_DIFFICULTY_01);
        Assert.assertEquals(new ObjectId(RecipesExamples.RECIPE_ID_01), recipe.getId());
        Assert.assertEquals(RecipesExamples.RECIPE_NAME_01, recipe.getName());
        Assert.assertEquals(RecipesExamples.RECIPE_DESCRIPTION_01, recipe.getDescription());
        Assert.assertNotNull(recipe.getCuisineType());
        Assert.assertEquals(2, recipe.getCuisineType().size());
        Assert.assertEquals(RecipesExamples.RECIPE_01_CUISINE_TYPE_01, recipe.getCuisineType().get(0));
        Assert.assertEquals(RecipesExamples.RECIPE_01_CUISINE_TYPE_02, recipe.getCuisineType().get(1));
        Assert.assertEquals(RecipesExamples.RECIPE_PREPARATION_TIME_01, recipe.getPreparationTime());
        Assert.assertEquals(RecipesExamples.RECIPE_DIFFICULTY_01, recipe.getDifficulty());
    }

}
