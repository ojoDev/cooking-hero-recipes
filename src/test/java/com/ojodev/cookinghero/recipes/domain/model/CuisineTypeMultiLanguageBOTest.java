package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypeMultiLanguageBOTest {


    @Test
    public void createCuisineTypeMultiLanguageBOWithDefaultLanguage() {
        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH);
        CuisineTypeMultiLanguageBO cuisineType = new CuisineTypeMultiLanguageBO.Builder(
                languageNameList,
                CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH)
                .build();

        assertNotNull(cuisineType);
        assertEquals(1, cuisineType.getLanguageNames().size());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH.getLanguage(), cuisineType.getLanguageNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH.getName(), cuisineType.getLanguageNames().get(0).getName());

    }

    @Test
    public void createCuisineTypeMultiLanguageBOWithNoDefaultLanguage() {
        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(CuisineTypesExamples.LANGUAGE_NAME_BO_01_SPANISH);

        try {
            CuisineTypeMultiLanguageBO cuisineType = new CuisineTypeMultiLanguageBO.Builder(
                    languageNameList,
                    CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH)
                    .build();
            fail("Need to be throw an exception");
        } catch (IllegalArgumentException e) {
            // Ok, needs to include default language
        }
    }

        @Test
        public void createCuisineTypeMultiLanguageBOMultipleLanguages() {
            List<LanguageNameBO> languageNameList = new ArrayList<>();
            languageNameList.add(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH);

                CuisineTypeMultiLanguageBO cuisineType = new CuisineTypeMultiLanguageBO.Builder(
                        languageNameList,
                        CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH)
                        .languageName(CuisineTypesExamples.LANGUAGE_NAME_BO_01_SPANISH)
                        .build();

            assertNotNull(cuisineType);
            assertEquals(2, cuisineType.getLanguageNames().size());
            assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH.getLanguage(), cuisineType.getLanguageNames().get(0).getLanguage());
            assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH.getName(), cuisineType.getLanguageNames().get(0).getName());
            assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_SPANISH.getLanguage(), cuisineType.getLanguageNames().get(1).getLanguage());
            assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_SPANISH.getName(), cuisineType.getLanguageNames().get(1).getName());
    }

    @Test
    public void createCuisineTypeMultiLanguageBOMultipleLanguagesWithReplace() {
        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH);

        CuisineTypeMultiLanguageBO cuisineType = new CuisineTypeMultiLanguageBO.Builder(
                languageNameList,
                CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH)
                .languageName(CuisineTypesExamples.LANGUAGE_NAME_BO_01_SPANISH)
                .languageName(CuisineTypesExamples.LANGUAGE_NAME_BO_02_ENGLISH)
                .languageName(CuisineTypesExamples.LANGUAGE_NAME_BO_02_SPANISH)
                .build();

        assertNotNull(cuisineType);
        assertEquals(2, cuisineType.getLanguageNames().size());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_02_ENGLISH.getLanguage(), cuisineType.getLanguageNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_02_ENGLISH.getName(), cuisineType.getLanguageNames().get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_02_SPANISH.getLanguage(), cuisineType.getLanguageNames().get(1).getLanguage());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_02_SPANISH.getName(), cuisineType.getLanguageNames().get(1).getName());
    }


}
