package com.ojodev.cookinghero.recipes.api.model;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptiveNameUpdateTest {

    @Test
    public void constructorAllFields() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL);

        assertNotNull(descriptiveNameUpdate);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveNameUpdate.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveNameUpdate.getPlural());
    }

    @Test
    public void testNullOptionals() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate(null, null);

        assertNotNull(descriptiveNameUpdate);
        assertNull(descriptiveNameUpdate.getSingular());
        assertNull(descriptiveNameUpdate.getPlural());
        assertFalse(descriptiveNameUpdate.getSingularOpt().isPresent());
        assertFalse(descriptiveNameUpdate.getPluralOpt().isPresent());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(DescriptiveNameUpdate.class);
    }
}