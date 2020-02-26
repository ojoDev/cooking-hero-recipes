package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import com.ojodev.cookinghero.recipes.utils.AbstractJavaBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureNewNameTest extends AbstractJavaBeanTest<MeasureNewName> {

    @Override
    protected MeasureNewName getBeanInstance() {
        return new MeasureNewName();
    }

    @Test
    public void constructorAllFields() {
        DescriptiveName descriptiveName = new DescriptiveName(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);
        MeasureNewName measureNewName = new MeasureNewName(descriptiveName, LanguageEnum.EN);

        assertNotNull(measureNewName);
        assertEquals(LanguageEnum.EN, measureNewName.getLanguage());
        assertNotNull(measureNewName.getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, measureNewName.getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, measureNewName.getName().getPlural());
    }
}
