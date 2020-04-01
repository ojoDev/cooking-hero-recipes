package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeNewBOTest extends AbstractJavaBeanTest<RecipeNewBO> {

    @Override
    protected RecipeNewBO getBeanInstance() {
        return new RecipeNewBO();
    }

    @Test
    public void constructorAllFields() {
        RecipeNewBO recipeNewBO = new RecipeNewBO(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);

        assertNotNull(recipeNewBO);
        assertNotNull(recipeNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, recipeNewBO.getName());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, recipeNewBO.getUserId());
    }

}

