package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNewName;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesMultipleLanguageMapperTest {

    @Autowired
    private CuisineTypesMultipleLanguageMapper cuisineTypesMultipleLanguageMapper;

    @Test
    public void convertCuisineTypeNewToCuisineTypeMultiLanguageBO() {

        CuisineTypeNew cuisineTypeNew = new CuisineTypeNew(Arrays.asList(
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnum.EN),
                new CuisineTypeNewName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES)));

        CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO = cuisineTypesMultipleLanguageMapper.toCuisineTypeMultiLanguageBO(cuisineTypeNew, LanguageEnumBO.EN);

        assertNotNull(cuisineTypeMultiLanguageBO);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeMultiLanguageBO.getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeMultiLanguageBO.getLanguageNames().get(0).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypeMultiLanguageBO.getLanguageNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypeMultiLanguageBO.getLanguageNames().get(1).getName());
        assertEquals(LanguageEnumBO.ES, cuisineTypeMultiLanguageBO.getLanguageNames().get(1).getLanguage());
    }

    @Test
    public void convertCuisineTypeMultiLanguageBOToCuisineTypePO() {

        CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO = new CuisineTypeMultiLanguageBO.Builder(Arrays.asList(
                new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN),
                new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES)), LanguageEnumBO.EN).build();

        CuisineTypePO cuisineTypePO = cuisineTypesMultipleLanguageMapper.toCuisineTypePO(cuisineTypeMultiLanguageBO);

        assertNotNull(cuisineTypePO);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypePO.getObjectId());
        assertNotNull(cuisineTypePO.getNames());
        assertEquals(2, cuisineTypePO.getNames().size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypePO.getNames().get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_EN, cuisineTypePO.getNames().get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypePO.getNames().get(1).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ES, cuisineTypePO.getNames().get(1).getLanguage());
    }
}
