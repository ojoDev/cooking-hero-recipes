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
public class MeasurePOTest {

    @Test
    public void constructorAllFields() {

        DescriptiveNamePO descriptiveName = new DescriptiveNamePO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, DescriptiveNamesExamples.LANGUAGE_ENGLISH);
        MeasurePO measure = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(descriptiveName));

        assertNotNull(measure);
        assertEquals(MeasuresExamples.MEASURE_01_ID, measure.getObjectId());
        assertNotNull(measure.getNames());
        assertEquals(1, measure.getNames().size());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, measure.getNames().get(0).getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, measure.getNames().get(0).getPlural());
        assertEquals(DescriptiveNamesExamples.LANGUAGE_ENGLISH, measure.getNames().get(0).getLanguage());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(MeasurePO.class);
    }
}
