package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Step;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import com.ojodev.cookinghero.recipes.domain.model.StepBO;
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
        //TODO DMS Falta gestión de Media
        StepPO stepPO = new StepPO(StepsExamples.STEP_01_ID.toString(), StepsExamples.STEP_01_DESCRIPTION);

        StepBO stepBO = stepsMapper.toStepBO(stepPO);

        assertNotNull(stepBO);
        assertEquals(StepsExamples.STEP_01_ID, stepBO.getId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepBO.getDescription());
    }

    @Test
    public void convertStepBOToStep() {
        //TODO DMS Falta gestión de Media
        StepBO stepBO = new StepBO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_DESCRIPTION);

        Step step = stepsMapper.toStep(stepBO);

        assertNotNull(step);
        assertEquals(Integer.valueOf(StepsExamples.STEP_01_ID), step.getId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, step.getDescription());
    }
}
