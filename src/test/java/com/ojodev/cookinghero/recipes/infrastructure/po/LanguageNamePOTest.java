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
public class LanguageNamePOTest {


    @Test
    public void constructorAllFields() {
        LanguageNamePO languageNamePO = new LanguageNamePO(DescriptiveNamesExamples.LANGUAGE_ENGLISH, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR);

        assertNotNull(languageNamePO);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, languageNamePO.getName());
        assertEquals(DescriptiveNamesExamples.LANGUAGE_ENGLISH, languageNamePO.getLanguage());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(LanguageNamePO.class);
    }

}
