package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.enume.RecipeStatusEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.RecipeNewBO;
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

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipesBusinessAddRecipeTest {

    @Autowired
    private RecipesBusiness recipesBusiness;

    @MockBean
    private RecipesRepository recipesRepository;

    @Test
    public void addNewRecipeBasicFields() {

        RecipeNewBO recipe = new RecipeNewBO(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);
        String id = recipe.getId();;

        Assertions.assertDoesNotThrow(() -> recipesBusiness.addRecipe(recipe));

        ArgumentCaptor<RecipePO> argumentRecipePO = ArgumentCaptor.forClass(RecipePO.class);
        verify(recipesRepository).save(argumentRecipePO.capture());
        assertNotNull(argumentRecipePO);
        assertNull(argumentRecipePO.getValue().getId());
        assertEquals(id, argumentRecipePO.getValue().getObjectId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, argumentRecipePO.getValue().getName());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, argumentRecipePO.getValue().getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE.toString(), argumentRecipePO.getValue().getLanguage());
        assertEquals(RecipeStatusEnumBO.DRAFT.toString(), argumentRecipePO.getValue().getStatus());

    }

    @Test
    public void addNewRecipeAllFields() {

        RecipeNewBO recipe = new RecipeNewBO(RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_USER_ID);
        recipe.setDescription(RecipesExamples.RECIPE_01_DESCRIPTION);
        recipe.setPreparationTime(RecipesExamples.RECIPE_01_PREPARATION_TIME);
        recipe.setDifficulty(RecipesExamples.RECIPE_01_DIFFICULTY);
        String id = recipe.getId();

        Assertions.assertDoesNotThrow(() -> recipesBusiness.addRecipe(recipe));

        ArgumentCaptor<RecipePO> argumentRecipePO = ArgumentCaptor.forClass(RecipePO.class);
        verify(recipesRepository).save(argumentRecipePO.capture());
        assertNotNull(argumentRecipePO);
        assertNull(argumentRecipePO.getValue().getId());
        assertEquals(id, argumentRecipePO.getValue().getObjectId());
        assertEquals(RecipesExamples.RECIPE_01_NAME, argumentRecipePO.getValue().getName());
        assertEquals(RecipesExamples.RECIPE_01_DESCRIPTION, argumentRecipePO.getValue().getDescription());
        assertEquals(RecipesExamples.RECIPE_01_PREPARATION_TIME, argumentRecipePO.getValue().getPreparationTime());
        assertEquals(RecipesExamples.RECIPE_01_DIFFICULTY, argumentRecipePO.getValue().getDifficulty());
        assertEquals(RecipesExamples.RECIPE_01_USER_ID, argumentRecipePO.getValue().getUserId());
        assertEquals(RecipesExamples.RECIPE_01_LANGUAGE.toString(), argumentRecipePO.getValue().getLanguage());
        assertEquals(RecipeStatusEnumBO.DRAFT.toString(), argumentRecipePO.getValue().getStatus());

    }

    // TODO DMS: Faltan test de usuario y lenguaje


}
