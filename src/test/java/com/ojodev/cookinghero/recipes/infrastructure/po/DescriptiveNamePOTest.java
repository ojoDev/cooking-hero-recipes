package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptiveNamePOTest {

    @Test
    public void constructorAllFields() {
        DescriptiveNamePO descriptiveName = new DescriptiveNamePO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, DescriptiveNamesExamples.LANGUAGE_ENGLISH);

        assertNotNull(descriptiveName);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, descriptiveName.getSingular());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_PLURAL, descriptiveName.getPlural());
        assertEquals( DescriptiveNamesExamples.LANGUAGE_ENGLISH, descriptiveName.getLanguage());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(DescriptiveNamePO.class);
    }
}