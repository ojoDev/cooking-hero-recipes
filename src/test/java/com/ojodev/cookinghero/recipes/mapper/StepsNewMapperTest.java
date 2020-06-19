package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.MediaRef;
import com.ojodev.cookinghero.recipes.api.model.StepNew;
import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import com.ojodev.cookinghero.recipes.domain.model.StepNewBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepsNewMapperTest {

    @Autowired
    private StepsNewMapper stepsNewMapper;

    @Test
    public void convertStepNewToStepNewBO() {

        MediaRef media = new MediaRef(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE);

        StepNew stepNew = new StepNew(StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION, media);

        StepNewBO stepNewBO = stepsNewMapper.toStepNewBO(stepNew, RecipesExamples.RECIPE_01_ID);

        assertNotNull(stepNewBO);
        assertEquals(RecipesExamples.RECIPE_01_ID + "-" + StepsExamples.STEP_01_POSITION, stepNewBO.getId());
        assertEquals(StepsExamples.STEP_01_POSITION, stepNewBO.getPosition());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepNewBO.getDescription());
        assertNotNull(stepNewBO.getMedia());
        assertEquals(MediaExamples.MEDIA_01_ID, stepNewBO.getMedia().getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, stepNewBO.getMedia().getMediaType());
    }

    @Test
    public void convertStepNewPOToStepPO() {

      //TODO DMS FAlta implementar
    }


}
