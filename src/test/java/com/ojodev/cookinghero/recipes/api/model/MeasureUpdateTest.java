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
public class MeasureUpdateTest extends AbstractJavaBeanTest<MeasureUpdate> {

    @Override
    protected MeasureUpdate getBeanInstance() {
        return new MeasureUpdate();
    }

    @Test
    public void constructorAllFields() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);
        MeasureUpdate measureUpdate = new MeasureUpdate(descriptiveNameUpdate);

        assertNotNull(measureUpdate);
        assertNotNull(measureUpdate.getName());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, measureUpdate.getName().getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, measureUpdate.getName().getPlural());
    }
}
