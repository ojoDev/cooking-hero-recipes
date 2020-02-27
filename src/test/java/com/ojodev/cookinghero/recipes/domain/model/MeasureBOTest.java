package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureBOTest extends AbstractJavaBeanTest<MeasureBO> {

    @Override
    protected MeasureBO getBeanInstance() {
        return new MeasureBO();
    }

    @Test
    public void constructorAllFields() {

        DescriptiveNameBO descriptiveName = new DescriptiveNameBO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, LanguageEnumBO.EN);
        MeasureBO measure = new MeasureBO(MeasuresExamples.MEASURE_01_ID, descriptiveName);

        assertNotNull(measure);
        assertEquals(MeasuresExamples.MEASURE_01_ID, measure.getId());
        assertNotNull(measure.getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, measure.getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, measure.getName().getPlural());
        assertEquals(LanguageEnumBO.EN, measure.getName().getLanguage());
    }
}
