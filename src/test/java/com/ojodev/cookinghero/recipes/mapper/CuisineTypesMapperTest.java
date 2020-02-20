package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesMapperTest {

    @Autowired
    private CuisineTypesMapper cuisineTypeMapper;


    @Test
    public void convertCuisineTypeBOToCuisineType() {

        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        CuisineType cuisineType = cuisineTypeMapper.toCuisineType(cuisineTypeBO);
        assertNotNull(cuisineType);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineType.getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType.getName());
    }


    @Test
    public void convertCuisineTypeBOListToCuisineTypeList() {

        CuisineTypeBO cuisineTypeBO01 = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        CuisineTypeBO cuisineTypeBO02 = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_02_ID, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, LanguageEnumBO.EN);

        List<CuisineType> cuisineTypes = cuisineTypeMapper.toCuisineTypeList(Arrays.asList(cuisineTypeBO01, cuisineTypeBO02));
        assertNotNull(cuisineTypes);
        assertEquals(2, cuisineTypes.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypes.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypes.get(0).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypes.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypes.get(1).getName());
    }

    @Test
    public void convertCuisineTypePOToCuisineTypeBO() {

        CuisineTypePO cuisineTypePO = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));

        CuisineTypeBO cuisineTypeEnBO = cuisineTypeMapper.toCuisineTypeBO(cuisineTypePO, LanguageEnumBO.EN);
        assertNotNull(cuisineTypeEnBO);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypeEnBO.getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeEnBO.getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypeEnBO.getLanguage());

        CuisineTypeBO cuisineTypeEsBO = cuisineTypeMapper.toCuisineTypeBO(cuisineTypePO, LanguageEnumBO.ES);
        assertNotNull(cuisineTypeEsBO);
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypeEsBO.getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypeEsBO.getName());
        assertEquals(LanguageEnumBO.ES, cuisineTypeEsBO.getLanguage());
    }


}
