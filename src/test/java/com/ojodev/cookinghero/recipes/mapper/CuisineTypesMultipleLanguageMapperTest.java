package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesMultipleLanguageMapperTest {

    @Autowired
    private CuisineTypesMultipleLanguageMapper cuisineTypesMultipleLanguageMapper;

    @Test
    public void convertCuisineTypeNewToCuisineTypeMultiLanguageBO() {
        CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO = cuisineTypesMultipleLanguageMapper.toCuisineTypeMultiLanguageBO(CuisineTypesExamples.CUISINE_TYPE_NEW, CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH);
        assertNotNull(cuisineTypeMultiLanguageBO);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_NEW.getNames().get(0).getName(), cuisineTypeMultiLanguageBO.getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_NEW.getNames().get(0).getName(), cuisineTypeMultiLanguageBO.getLanguageNames().get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypeMultiLanguageBO.getLanguageNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_NEW.getNames().get(1).getName(), cuisineTypeMultiLanguageBO.getLanguageNames().get(1).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_SPANISH, cuisineTypeMultiLanguageBO.getLanguageNames().get(1).getLanguage());
    }

    @Test
    public void convertCuisineTypeMultiLanguageBOToCuisineTypePO() {
        CuisineTypePO cuisineTypePO = cuisineTypesMultipleLanguageMapper.toCuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_MULTI_LANGUAGE_BO);
        assertNotNull(cuisineTypePO);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH,cuisineTypePO.getObjectId());
        assertNotNull(cuisineTypePO.getNames());
        assertEquals(2, cuisineTypePO.getNames().size());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_ENGLISH.getName(),cuisineTypePO.getNames().get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_EN,cuisineTypePO.getNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.LANGUAGE_NAME_BO_01_SPANISH.getName(),cuisineTypePO.getNames().get(1).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ES,cuisineTypePO.getNames().get(1).getLanguage());
    }
}
