package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MediaRefPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class StepsRepositoryTest {

    @Autowired
    private StepsRepository stepsRepository;

    @Autowired
    private RecipesRepository recipesRepository;

    private static final String RECIPE_ID_STEPS_REPOSITORY_TEST = "test-recipe-id-steps-repository-test";
    private static final String STEP_ID_STEPS_REPOSITORY_TEST = "test-step-id-steps-repository-test";
    private static final String MEDIA_ID_STEPS_REPOSITORY_TEST = "test-media-id-steps-repository-test";

    @Before
    public void initData() {
        RecipePO recipePO = new RecipePO(RECIPE_ID_STEPS_REPOSITORY_TEST, RecipesExamples.RECIPE_01_NAME, RecipesExamples.RECIPE_01_DESCRIPTION, LanguageEnumBO.EN);
        recipePO.setUserId(RecipesExamples.RECIPE_01_USER_ID);
        recipesRepository.save(recipePO);

        MediaRefPO mediaRef = new MediaRefPO(MEDIA_ID_STEPS_REPOSITORY_TEST, MediaExamples.MEDIA_01_TYPE_BO.toString());

        StepPO newStep = new StepPO(STEP_ID_STEPS_REPOSITORY_TEST, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION, mediaRef);
        newStep.setRecipe(recipePO);
        stepsRepository.save(newStep, 2);

    }

    @Test
    public void addAndGetStep() {
        RecipePO recipe = recipesRepository.findByObjectId(RECIPE_ID_STEPS_REPOSITORY_TEST).get(0);

        MediaRefPO mediaRef = new MediaRefPO(MEDIA_ID_STEPS_REPOSITORY_TEST, MediaExamples.MEDIA_01_TYPE_BO.toString());

        StepPO newStep = new StepPO(STEP_ID_STEPS_REPOSITORY_TEST, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION, mediaRef);
        newStep.setRecipe(recipe);
        stepsRepository.save(newStep, 2);

        List<StepPO> steps = stepsRepository.findStepInRecipe(RECIPE_ID_STEPS_REPOSITORY_TEST, STEP_ID_STEPS_REPOSITORY_TEST);

        assertNotNull(steps);
        assertEquals(1, steps.size());
        StepPO stepPO = steps.get(0);
        assertNotNull(stepPO.getId());
        assertEquals(STEP_ID_STEPS_REPOSITORY_TEST, stepPO.getObjectId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepPO.getDescription());
        assertNotNull(StepsExamples.STEP_01_DESCRIPTION, stepPO.getRecipe());
        assertEquals(RECIPE_ID_STEPS_REPOSITORY_TEST, stepPO.getRecipe().getObjectId());
        assertNotNull(stepPO.getMedia());
        assertNotNull(stepPO.getMedia().getId());
        assertEquals(MEDIA_ID_STEPS_REPOSITORY_TEST, stepPO.getMedia().getObjectId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO.toString(), stepPO.getMedia().getMediaType());

        stepsRepository.delete(newStep);
    }

    @Test
    public void findStepsInRecipe() {


    }


    @After
    public void deleteData() {
        RecipePO recipe = recipesRepository.findByObjectId(RECIPE_ID_STEPS_REPOSITORY_TEST).get(0);
        List<StepPO> steps = recipe.getSteps();
        if (steps != null) {
            for (StepPO stepPO : steps) {
                stepsRepository.delete(stepPO);
            }
        }
        recipesRepository.delete(recipe);
    }



}
