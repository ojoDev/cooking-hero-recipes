package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.api.model.DescriptiveNameUpdate;
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
public class LanguageNameBOTest {


    @Test
    public void constructorAllFields() {
        LanguageNameBO languageNameBO = new LanguageNameBO(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, LanguageEnumBO.EN);

        assertNotNull(languageNameBO);
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, languageNameBO.getName());
        assertEquals(LanguageEnumBO.EN, languageNameBO.getLanguage());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(DescriptiveNameUpdate.class);
    }
}
