package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.api.model.Media;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.RecipesRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipesBusinessAddRecipeTest {

    @Autowired
    private RecipesBusiness recipesBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private RecipesRepository recipesRepository;

    @Test
    public void addNewRecipeBasicFields() {

        RecipeNewBO recipe = new RecipeNewBO(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID, RecipesExamples.RECIPE_01_LANGUAGE);
        String id = recipe.getId();

        when(this.recipesRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> recipesBusiness.addRecipe(recipe));

        ArgumentCaptor<RecipePO> argumentRecipePO = ArgumentCaptor.forClass(RecipePO.class);
        verify(recipesRepository).save(argumentRecipePO.capture());
        assertNotNull(argumentRecipePO);
        assertEquals(id, argumentRecipePO.getValue().getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, argumentRecipePO.getValue().getName());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, argumentRecipePO.getValue().getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, argumentRecipePO.getValue().getLanguage());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, argumentRecipePO.getValue().getStatus());

    }

    @Test
    public void addNewRecipeAllFields() {

        RecipeNewBO recipe = new RecipeNewBO(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID, RecipesExamples.RECIPE_01_LANGUAGE);
        recipe.setDescription(RecipesExamples.RECIPE_01_DESCRIPTION);
        recipe.setPreparationTime(RecipesExamples.RECIPE_01_PREPARATION_TIME);
        recipe.setDifficulty(RecipesExamples.RECIPE_01_DIFFICULTY);
        String id = recipe.getId();

        when(this.recipesRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> recipesBusiness.addRecipe(recipe));

        ArgumentCaptor<RecipePO> argumentRecipePO = ArgumentCaptor.forClass(RecipePO.class);
        verify(recipesRepository).save(argumentRecipePO.capture());
        assertNotNull(argumentRecipePO);
        assertEquals(id, argumentRecipePO.getValue().getId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, argumentRecipePO.getValue().getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, argumentRecipePO.getValue().getDescription());
        assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, argumentRecipePO.getValue().getPreparationTime());
        assertEquals(RecipesExamples.RECIPE_01_DIFFICULTY, argumentRecipePO.getValue().getDifficulty());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, argumentRecipePO.getValue().getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE, argumentRecipePO.getValue().getLanguage());

    }




}
