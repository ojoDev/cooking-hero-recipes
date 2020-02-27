package com.ojodev.cookinghero.recipes.infrastructure.po;

import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.data.DescriptiveNamesExamples;
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
public class CuisineTypePOTest {
    @Test
    public void constructorAllFields() {
        LanguageNamePO languageNamePO = new LanguageNamePO(DescriptiveNamesExamples.LANGUAGE_ENGLISH, DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR);

        CuisineTypePO cuisineType = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(languageNamePO));

        assertNotNull(cuisineType);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineType.getObjectId());
        assertNotNull(cuisineType.getNames());
        assertEquals(1, cuisineType.getNames().size());
        assertEquals(DescriptiveNamesExamples.DESCRIPTIVE_NAME_01_SINGULAR, cuisineType.getNames().get(0).getName());
        assertEquals(DescriptiveNamesExamples.LANGUAGE_ENGLISH, cuisineType.getNames().get(0).getLanguage());
    }

    @Test
    public void getterAndSetterCorrectness() {
        final BeanTester beanTester = new BeanTester();
        beanTester.testBean(CuisineTypePO.class);
    }

}
