package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.RecipesApplication;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.domain.model.ProductStatusEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@SpringBootTest(classes = RecipesApplication.class)
@Slf4j
public class StepsRepositoryTest {

    @Autowired
    private StepsRepository stepsRepository;

    @Autowired
    private RecipesRepository recipesRepository;

    @Test
    @Ignore
    public void addStep() {
        RecipePO recipe = recipesRepository.findByObjectId(RecipesExamples.RECIPE_ID_01).get(0);

        StepPO newStep = new StepPO("testId", "Add salt.", null);
        newStep.setRecipe(recipe);

        stepsRepository.save(newStep);

        StepPO step = stepsRepository.findByObjectId("testId").get(0);

        assertNotNull(step);

    }

}
