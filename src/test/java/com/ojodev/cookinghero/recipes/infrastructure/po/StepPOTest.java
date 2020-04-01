package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StepPOTest {

    @Test
    public void constructorAllFields() {

        StepPO stepPO = new StepPO(MeasuresExamples.MEASURE_01_ID, MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR);

        assertNotNull(stepPO);
        assertEquals(MeasuresExamples.MEASURE_01_ID, stepPO.getObjectId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, stepPO.getDescription());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(StepPO.class);
    }
}
