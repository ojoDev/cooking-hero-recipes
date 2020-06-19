package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.MediaExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepPOTest {

    @Test
    public void constructorThreeFields() {

        StepPO stepPO = new StepPO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION);

        assertNotNull(stepPO);
        assertEquals(StepsExamples.STEP_01_ID, stepPO.getObjectId());
        assertEquals(StepsExamples.STEP_01_POSITION, stepPO.getPosition());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepPO.getDescription());
    }

    @Test
    public void constructorAllFields() {

        MediaRefPO mediaRefPO = new MediaRefPO(MediaExamples.MEDIA_01_ID, MediaExamples.MEDIA_01_TYPE_BO.toString());
        StepPO stepPO = new StepPO(StepsExamples.STEP_01_ID, StepsExamples.STEP_01_POSITION, StepsExamples.STEP_01_DESCRIPTION, mediaRefPO);

        assertNotNull(stepPO);
        assertEquals(StepsExamples.STEP_01_ID, stepPO.getObjectId());
        assertEquals(StepsExamples.STEP_01_POSITION, stepPO.getPosition());
        assertEquals(StepsExamples.STEP_01_DESCRIPTION, stepPO.getDescription());
        assertNotNull(stepPO.getMedia());
        assertEquals(MediaExamples.MEDIA_01_ID, stepPO.getMedia().getObjectId());
        assertEquals(MediaExamples.MEDIA_01_TYPE_BO.toString(), stepPO.getMedia().getMediaType());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(StepPO.class);
    }
}
