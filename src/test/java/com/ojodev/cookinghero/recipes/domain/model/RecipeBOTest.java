package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.enume.MediaTypeEnumBO;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeBOTest {

    @Test
    public void constructorTwoFields() {
        RecipeBO recipeBO = new RecipeBO(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);

        assertNotNull(recipeBO);
        assertNotNull(recipeBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipeBO.getName());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipeBO.getUserId());
    }

    @Test
    public void constructorThreeFields() {
        RecipeBO recipeBO = new RecipeBO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);

        assertNotNull(recipeBO);
        assertEquals(RecipesExamples.RECIPE_01_ID, recipeBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipeBO.getName());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipeBO.getUserId());
    }

    @Test
    public void setVideoAsMainImage() {
        RecipeBO recipeBO = new RecipeBO(RecipesExamples.RECIPE_01_ID, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            recipeBO.setMainImage(new MediaRefBO(MediaExamples.MEDIA_01_ID, MediaTypeEnumBO.VIDEO));
        });
    }

}