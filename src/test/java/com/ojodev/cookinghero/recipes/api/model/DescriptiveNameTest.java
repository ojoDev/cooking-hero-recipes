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
public class DescriptiveNameTest extends AbstractJavaBeanTest<DescriptiveName> {

    @Override
    protected DescriptiveName getBeanInstance() {
        return new DescriptiveName();
    }

    @Test
    public void constructorAllFields() {
        DescriptiveName descriptiveName = new DescriptiveName(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);

        assertNotNull(descriptiveName);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveName.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveName.getPlural());
    }
}