package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipesBusinessExistsRecipeTest {


    @Autowired
    private RecipesBusiness recipesBusiness;

    @MockBean
    private RecipesRepository recipesRepository;


    @Test
    public void existsRecipeTrueTest() {

        when(recipesRepository.existsByObjectId(RecipesExamples.RECIPE_01_ID)).thenReturn(true);

        boolean exists = recipesBusiness.existsRecipe(RecipesExamples.RECIPE_01_ID);
        assertTrue(exists);
    }

    @Test
    public void existsRecipeFalseTest() {

        when(recipesRepository.existsByObjectId(RecipesExamples.RECIPE_01_ID)).thenReturn(false);

        boolean exists = recipesBusiness.existsRecipe(RecipesExamples.RECIPE_01_ID);

        assertFalse(exists);

    }

}
