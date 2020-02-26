package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureNewTest extends AbstractJavaBeanTest<MeasureNew> {

    @Override
    protected MeasureNew getBeanInstance() {
        return new MeasureNew();
    }

    @Test
    public void constructorAllFields() {
        DescriptiveName descriptiveName = new DescriptiveName(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);
        MeasureNewName measureNewName = new MeasureNewName(descriptiveName, LanguageEnum.EN);
        DescriptiveName descriptiveName02 = new DescriptiveName(DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_PLURAL);
        MeasureNewName measureNewName02 = new MeasureNewName(descriptiveName02, LanguageEnum.ES);
        MeasureNew measureNew = new MeasureNew(Arrays.asList(measureNewName, measureNewName02));

        assertNotNull(measureNew);
        assertNotNull(measureNew.getNames());
        assertEquals(2, measureNew.getNames().size());
        assertEquals(LanguageEnum.EN, measureNew.getNames().get(0).getLanguage());
        assertNotNull(measureNew.getNames().get(0).getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, measureNew.getNames().get(0).getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, measureNew.getNames().get(0).getName().getPlural());
        assertEquals(LanguageEnum.ES, measureNew.getNames().get(1).getLanguage());
        assertNotNull(measureNew.getNames().get(1).getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_SINGULAR, measureNew.getNames().get(1).getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_02_PLURAL, measureNew.getNames().get(1).getName().getPlural());
    }
}
