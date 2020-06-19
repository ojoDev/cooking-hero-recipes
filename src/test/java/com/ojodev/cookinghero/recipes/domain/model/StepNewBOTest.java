package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepNewBOTest {

    @Test
    public void constructorTwoFields() {
        StepNewBO stepNewBO = new StepNewBO(RecipesExamples.RECIPE_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION);

        assertNotNull(stepNewBO);
        assertEquals(RecipesExamples.RECIPE_01_ID + "-" + StepsExamples.STEP_01_POSITION, stepNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_ID, stepNewBO.getRecipeId());
        assertEquals(StepsExamples.STEP_01_POSITION, stepNewBO.getPosition());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepNewBO.getDescription());
        assertNull(stepNewBO.getMedia());
    }

    @Test
    public void constructorThreeFields() {
        MediaRefBO mediaRefBO = new MediaRefBO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO);
        StepNewBO stepNewBO = new StepNewBO(RecipesExamples.RECIPE_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION, mediaRefBO);

        assertNotNull(stepNewBO);
        assertEquals(RecipesExamples.RECIPE_01_ID + "-" + StepsExamples.STEP_01_POSITION, stepNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_01_ID, stepNewBO.getRecipeId());
        assertEquals(StepsExamples.STEP_01_POSITION, stepNewBO.getPosition());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepNewBO.getDescription());
        assertNotNull(stepNewBO.getMedia());
        assertEquals(MediaExamples.MEDIA_01_ID, stepNewBO.getMedia().getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, stepNewBO.getMedia().getMediaType());
    }


    @Test
    public void getterAndSetterCorrectness() {
        MediaRefBO mediaRefBO = new MediaRefBO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO);

        StepNewBO stepNewBO = new StepNewBO(RecipesExamples.RECIPE_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION);
        stepNewBO.setId(StepsExamples.STEP_02_ID);
        stepNewBO.setRecipeId(RecipesExamples.RECIPE_02_ID);
        stepNewBO.setDescription(StepsExamples.STEP_02_DESCRIPTION);
        stepNewBO.setPosition(StepsExamples.STEP_02_POSITION);
        stepNewBO.setMedia(mediaRefBO);

        assertEquals(StepsExamples.STEP_02_ID, stepNewBO.getId());
        assertEquals(RecipesExamples.RECIPE_02_ID, stepNewBO.getRecipeId());
        assertEquals(StepsExamples.STEP_02_DESCRIPTION, stepNewBO.getDescription());
        assertEquals(StepsExamples.STEP_02_POSITION, stepNewBO.getPosition());
        assertNotNull(stepNewBO.getMedia());
        assertEquals(MediaExamples.MEDIA_01_ID, stepNewBO.getMedia().getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, stepNewBO.getMedia().getMediaType());
    }
}

