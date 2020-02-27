package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypeMultiLanguageBOTest {


    @Test
    public void createCuisineTypeMultiLanguageBOWithDefaultLanguage() {
        LanguageNameBO languageNameBOEn = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(languageNameBOEn);
        CuisineTypeMultiLanguageBO cuisineType = new CuisineTypeMultiLanguageBO.Builder(
                languageNameList,
                LanguageEnumBO.EN)
                .build();

        assertNotNull(cuisineType);
        assertEquals(1, cuisineType.getLanguageNames().size());
        assertEquals(LanguageEnumBO.EN, cuisineType.getLanguageNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType.getLanguageNames().get(0).getName());

    }

    @Test
    public void createCuisineTypeMultiLanguageBOWithNoDefaultLanguage() {

        LanguageNameBO languageNameBOEs = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);

        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(languageNameBOEs);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CuisineTypeMultiLanguageBO.Builder(
                            languageNameList,
                            LanguageEnumBO.EN)
                            .build();
                }, "Need to be throw an exception");
    }

    @Test
    public void createCuisineTypeMultiLanguageBOMultipleLanguages() {

        LanguageNameBO languageNameBOEn = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        LanguageNameBO languageNameBOEs = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);

        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(languageNameBOEn);

        CuisineTypeMultiLanguageBO cuisineType = new CuisineTypeMultiLanguageBO.Builder(
                languageNameList,
                LanguageEnumBO.EN)
                .languageName(languageNameBOEs)
                .build();

        assertNotNull(cuisineType);
        assertEquals(2, cuisineType.getLanguageNames().size());
        assertEquals(LanguageEnumBO.EN, cuisineType.getLanguageNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType.getLanguageNames().get(0).getName());
        assertEquals(LanguageEnumBO.ES, cuisineType.getLanguageNames().get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineType.getLanguageNames().get(1).getName());
    }

    @Test
    public void createCuisineTypeMultiLanguageBOMultipleLanguagesWithReplace() {

        LanguageNameBO languageNameBOEn01 = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        LanguageNameBO languageNameBOEn02 = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, LanguageEnumBO.EN);
        LanguageNameBO languageNameBOEs01 = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);
        LanguageNameBO languageNameBOEs02 = new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH, LanguageEnumBO.ES);

        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(languageNameBOEn01);

        CuisineTypeMultiLanguageBO cuisineType = new CuisineTypeMultiLanguageBO.Builder(
                languageNameList,
                LanguageEnumBO.EN)
                .languageName(languageNameBOEs01)
                .languageName(languageNameBOEn02)
                .languageName(languageNameBOEs02)
                .build();

        assertNotNull(cuisineType);
        assertEquals(2, cuisineType.getLanguageNames().size());
        assertEquals(LanguageEnumBO.EN, cuisineType.getLanguageNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineType.getLanguageNames().get(0).getName());
        assertEquals(LanguageEnumBO.ES, cuisineType.getLanguageNames().get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH, cuisineType.getLanguageNames().get(1).getName());
    }

    @Test
    public void createCuisineTypeMultiLanguageBOWithInvalidFields() {
        List<LanguageNameBO> languageNameList = new ArrayList<>();
        languageNameList.add(new LanguageNameBO(null, null));

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CuisineTypeMultiLanguageBO.Builder(
                            languageNameList,
                            LanguageEnumBO.EN)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Language name needs to include at least name and language fields", exception.getMessage());
    }

    @Test
    public void createCuisineTypeMultiLanguageBONoNames() {
        List<LanguageNameBO> languageNameList = new ArrayList<>();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new CuisineTypeMultiLanguageBO.Builder(
                            languageNameList,
                            LanguageEnumBO.EN)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("CuisineType needs to include at least one name", exception.getMessage());
    }

}
