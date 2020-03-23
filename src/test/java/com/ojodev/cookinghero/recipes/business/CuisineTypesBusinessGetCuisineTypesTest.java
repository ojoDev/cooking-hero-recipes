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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesBusinessGetCuisineTypesTest {

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesRepository cuisineTypesRepository;

    @Test
    public void getAllCuisineTypesByLanguage() {

        CuisineTypePO cuisineTypePO01 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO02 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_02_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO03 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_03_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_03_NAME_SPANISH)));

        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(cuisineTypePO01, cuisineTypePO02, cuisineTypePO03));

        List<CuisineTypeBO> cuisineTypesEn = cuisineTypesBusiness.getCuisineTypes(LanguageEnumBO.EN);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEn.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypesEn.get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEn.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypesEn.get(1).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypesEn.get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEn.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH, cuisineTypesEn.get(2).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypesEn.get(2).getLanguage());

        List<CuisineTypeBO> cuisineTypesEs = cuisineTypesBusiness.getCuisineTypes(LanguageEnumBO.ES);

        assertEquals(3, cuisineTypesEs.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEs.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypesEs.get(0).getName());
        assertEquals(LanguageEnumBO.ES, cuisineTypesEs.get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEs.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH, cuisineTypesEs.get(1).getName());
        assertEquals(LanguageEnumBO.ES, cuisineTypesEs.get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEs.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_SPANISH, cuisineTypesEs.get(2).getName());
        assertEquals(LanguageEnumBO.ES, cuisineTypesEs.get(2).getLanguage());
    }

    @Test
    public void getAllCuisineTypesByNameAndLanguage() {

        CuisineTypePO cuisineTypePO01 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO02 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_02_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH)));

        when(this.cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, CuisineTypesExamples.LANGUAGE_EN)).thenReturn(Arrays.asList(cuisineTypePO01));
        when(this.cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, CuisineTypesExamples.LANGUAGE_EN)).thenReturn(Arrays.asList(cuisineTypePO02));
        when(this.cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, CuisineTypesExamples.LANGUAGE_ES)).thenReturn(Arrays.asList(cuisineTypePO01));

        List<CuisineTypeBO> cuisineTypes01English = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        assertEquals(1, cuisineTypes01English.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypes01English.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypes01English.get(0).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypes01English.get(0).getLanguage());

        List<CuisineTypeBO> cuisineTypes02English = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, LanguageEnumBO.EN);
        assertEquals(1, cuisineTypes02English.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypes02English.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypes02English.get(0).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypes02English.get(0).getLanguage());

        List<CuisineTypeBO> cuisineTypes01Spanish = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);
        assertEquals(1, cuisineTypes01Spanish.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypes01Spanish.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypes01Spanish.get(0).getName());
        assertEquals(LanguageEnumBO.ES, cuisineTypes01Spanish.get(0).getLanguage());
    }


    @Test
    public void getAllCuisineTypesWithDefaultLanguage() {

        CuisineTypePO cuisineTypePO01 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO02 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_02_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO03 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_03_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_03_NAME_SPANISH)));

        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(cuisineTypePO01, cuisineTypePO02, cuisineTypePO03));

        List<CuisineTypeBO> cuisineTypesEn = cuisineTypesBusiness.getCuisineTypes(null);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEn.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypesEn.get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEn.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypesEn.get(1).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypesEn.get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEn.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH, cuisineTypesEn.get(2).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypesEn.get(2).getLanguage());
    }

    @Test
    public void getAllCuisineTypesByNullName() {

        CuisineTypePO cuisineTypePO01 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO02 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_02_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH)));
        CuisineTypePO cuisineTypePO03 = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_03_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_03_NAME_SPANISH)));

        when(this.cuisineTypesRepository.findByName(any(), eq(CuisineTypesExamples.LANGUAGE_EN))).thenReturn(Arrays.asList(cuisineTypePO03));
        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(cuisineTypePO01, cuisineTypePO02, cuisineTypePO03));

        List<CuisineTypeBO> cuisineTypesNullName = cuisineTypesBusiness.getCuisineTypes(null, LanguageEnumBO.EN);
        List<CuisineTypeBO> cuisineTypesNoName = cuisineTypesBusiness.getCuisineTypes(LanguageEnumBO.EN);

        assertEquals(3, cuisineTypesNullName.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesNullName.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesNullName.get(0).getName());
        assertEquals(LanguageEnumBO.EN, cuisineTypesNullName.get(0).getLanguage());

        assertEquals(3, cuisineTypesNoName.size());
        assertEquals(cuisineTypesNoName.get(0).getId(), cuisineTypesNoName.get(0).getId());
        assertEquals(cuisineTypesNoName.get(0).getName(), cuisineTypesNoName.get(0).getName());
        assertEquals(cuisineTypesNoName.get(0).getLanguage(), cuisineTypesNoName.get(0).getLanguage());

    }

}
