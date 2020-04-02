package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Step;
import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import com.ojodev.cookinghero.recipes.domain.model.MediaRefBO;
import com.ojodev.cookinghero.recipes.domain.model.StepBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MediaRefPO;
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
public class StepsMapperTest {

    @Autowired
    private StepsMapper stepsMapper;

    @Test
    public void convertStepPOToStepBO() {

        MediaRefPO mediaPO = new MediaRefPO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO.toString());

        StepPO stepPO = new StepPO(StepsExamples.STEP_01_ID.toString(), StepsExamples.STEP_01_DESCRIPTION, mediaPO);

        StepBO stepBO = stepsMapper.toStepBO(stepPO);

        assertNotNull(stepBO);
        assertEquals(StepsExamples.STEP_01_ID, stepBO.getId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepBO.getDescription());
        assertNotNull(stepBO.getMedia());
        assertEquals(MediaExamples.MEDIA_01_ID, stepBO.getMedia().getId());
        assertEquals( MediaExamples.MEDIA_01_TYPE_BO, stepBO.getMedia().getMediaType());
    }

    @Test
    public void convertStepBOToStep() {

        MediaRefBO mediaBO = new MediaRefBO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO);

        StepBO stepBO = new StepBO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_DESCRIPTION, mediaBO);

        Step step = stepsMapper.toStep(stepBO);

        assertNotNull(step);
        assertEquals(Integer.valueOf(StepsExamples.STEP_01_ID), step.getId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, step.getDescription());
        assertNotNull(step.getMedia());
        assertEquals(MediaExamples.MEDIA_01_ID, step.getMedia().getId());
        assertEquals( MediaExamples.MEDIA_01_TYPE, step.getMedia().getMediaType());
    }
}
