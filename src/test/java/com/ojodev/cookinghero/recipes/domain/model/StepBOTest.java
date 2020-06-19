package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepBOTest extends AbstractJavaBeanTest<StepBO> {

    @Override
    protected StepBO getBeanInstance() {
        return new StepBO();
    }

    @Test
    public void constructorTwoFields() {
        StepBO stepBO = new StepBO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION);

        assertNotNull(stepBO);
        assertEquals(StepsExamples.STEP_01_ID, stepBO.getId());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepBO.getDescription());
        assertNull(stepBO.getMedia());
    }

    @Test
    public void constructorThreeFields() {
        MediaRefBO mediaRefBO = new MediaRefBO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO);
        StepBO stepBO = new StepBO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION, mediaRefBO);

        assertNotNull(stepBO);
        assertEquals(StepsExamples.STEP_01_ID, stepBO.getId());
        assertEquals(StepsExamples.STEP_01_POSITION, stepBO.getPosition());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepBO.getDescription());
        assertNotNull(stepBO.getMedia());
        assertEquals(MediaExamples.MEDIA_01_ID, stepBO.getMedia().getId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO, stepBO.getMedia().getMediaType());
    }
}

