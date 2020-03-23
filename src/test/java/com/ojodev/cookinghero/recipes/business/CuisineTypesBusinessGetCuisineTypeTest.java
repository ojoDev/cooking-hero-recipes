package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesBusinessGetCuisineTypeTest {

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesRepository cuisineTypesRepository;

    @Test
    public void getCuisineTypeById() {

        CuisineTypePO cuisineTypePO01 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO02 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_02_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH)));

        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(cuisineTypePO01);
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_02_ID)).thenReturn(cuisineTypePO02);

        Optional<CuisineTypeBO> cuisineType01 = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineType01);
        assertTrue(cuisineType01.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineType01.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType01.get().getName());
        assertEquals(LanguageEnumBO.EN, cuisineType01.get().getLanguage());

        Optional<CuisineTypeBO> cuisineType02 = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_02_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineType02);
        assertTrue(cuisineType02.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineType02.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineType02.get().getName());
        assertEquals(LanguageEnumBO.EN, cuisineType02.get().getLanguage());
    }

    @Test
    public void getCuisineTypeByIdDifferentLanguages() {

        CuisineTypePO cuisineTypePO = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));

        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(cuisineTypePO);

        Optional<CuisineTypeBO> cuisineTypeEn = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineTypeEn);
        assertTrue(cuisineTypeEn.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypeEn.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeEn.get().getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypeEn.get().getLanguage());

        Optional<CuisineTypeBO> cuisineTypeEs = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.ES);
        assertNotNull(cuisineTypeEs);
        assertTrue(cuisineTypeEs.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypeEs.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypeEs.get().getName());
        assertEquals(LanguageEnumBO.ES, cuisineTypeEs.get().getLanguage());
    }

    @Test
    public void getCuisineTypeByIdNotFound() {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(null);

        Optional<CuisineTypeBO> cuisineTypeEn = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineTypeEn);
        assertFalse(cuisineTypeEn.isPresent());
    }

}
