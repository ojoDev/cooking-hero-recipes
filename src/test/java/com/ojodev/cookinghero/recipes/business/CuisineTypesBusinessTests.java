package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CuisineTypesBusinessTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @MockBean
    private CuisineTypesRepository cuisineTypesRepository;

    @Test
    public void getAllCuisineTypesByLanguage() throws Exception {

        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_01, CuisineTypesExamples.CUISINE_TYPE_PO_02, CuisineTypesExamples.CUISINE_TYPE_PO_03));

        List<CuisineTypeBO> cuisineTypesEn = cuisineTypesBusiness.getCuisineTypes(LanguageEnumBO.EN);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEn.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEn.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypesEn.get(1).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEn.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH, cuisineTypesEn.get(2).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(2).getLanguage());

        List<CuisineTypeBO> cuisineTypesEs = cuisineTypesBusiness.getCuisineTypes(LanguageEnumBO.ES);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEs.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypesEs.get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_SPANISH, cuisineTypesEs.get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEs.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH, cuisineTypesEs.get(1).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_SPANISH, cuisineTypesEs.get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEs.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_SPANISH, cuisineTypesEs.get(2).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_SPANISH, cuisineTypesEs.get(2).getLanguage());
    }

    @Test
    public void getAllCuisineTypesByNameAndLanguage() throws Exception {
        when(this.cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, CuisineTypesExamples.LANGUAGE_EN)).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_01));
        when(this.cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, CuisineTypesExamples.LANGUAGE_EN)).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_02));
        when(this.cuisineTypesRepository.findByName(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, CuisineTypesExamples.LANGUAGE_ES)).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_01));

        List<CuisineTypeBO> cuisineTypes01English = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        assertEquals(1, cuisineTypes01English.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypes01English.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypes01English.get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypes01English.get(0).getLanguage());

        List<CuisineTypeBO> cuisineTypes02English = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, LanguageEnumBO.EN);
        assertEquals(1, cuisineTypes02English.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypes02English.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypes02English.get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypes02English.get(0).getLanguage());

        List<CuisineTypeBO> cuisineTypes01Spanish = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);
        assertEquals(1, cuisineTypes01Spanish.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypes01Spanish.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypes01Spanish.get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_SPANISH, cuisineTypes01Spanish.get(0).getLanguage());
    }


    @Test
    public void getAllCuisineTypesWithDefaultLanguage() throws Exception {
        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_01, CuisineTypesExamples.CUISINE_TYPE_PO_02, CuisineTypesExamples.CUISINE_TYPE_PO_03));

        List<CuisineTypeBO> cuisineTypesEn = cuisineTypesBusiness.getCuisineTypes(null);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEn.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(0).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEn.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypesEn.get(1).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(1).getLanguage());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEn.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH, cuisineTypesEn.get(2).getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(2).getLanguage());

    }

    @Test
    public void getCuisineTypeById() throws Exception {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(CuisineTypesExamples.CUISINE_TYPE_PO_01);
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_02_ID)).thenReturn(CuisineTypesExamples.CUISINE_TYPE_PO_02);

        Optional<CuisineTypeBO> cuisineType01 = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineType01);
        assertTrue(cuisineType01.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineType01.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineType01.get().getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineType01.get().getLanguage());

        Optional<CuisineTypeBO> cuisineType02 = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_02_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineType02);
        assertTrue(cuisineType02.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineType02.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineType02.get().getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineType02.get().getLanguage());
    }

    @Test
    public void getCuisineTypeByIdDifferentLanguages() throws Exception {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(CuisineTypesExamples.CUISINE_TYPE_PO_01);

        Optional<CuisineTypeBO> cuisineTypeEn = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineTypeEn);
        assertTrue(cuisineTypeEn.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypeEn.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypeEn.get().getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypeEn.get().getLanguage());

        Optional<CuisineTypeBO> cuisineTypeEs = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.ES);
        assertNotNull(cuisineTypeEs);
        assertTrue(cuisineTypeEs.isPresent());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypeEs.get().getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypeEs.get().getName());
        assertEquals(CuisineTypesExamples.LANGUAGE_ENUM_SPANISH, cuisineTypeEs.get().getLanguage());
    }

    @Test
    public void getCuisineTypeByIdNotFound() throws Exception {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(null);

        Optional<CuisineTypeBO> cuisineTypeEn = cuisineTypesBusiness.getCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineTypeEn);
        assertFalse(cuisineTypeEn.isPresent());

    }

    @Test
    public void addNewCuisineTypes() throws Exception {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_MULTI_LANGUAGE_BO.getId())).thenReturn(null);
        when(this.cuisineTypesRepository.save(any())).thenReturn(null);
        cuisineTypesBusiness.addCuisineType(CuisineTypesExamples.CUISINE_TYPE_MULTI_LANGUAGE_BO);

    }

    @Test
    public void addExistentCuisineTypes()  {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_MULTI_LANGUAGE_BO.getId())).thenReturn(CuisineTypesExamples.CUISINE_TYPE_PO_01);
        try{
            cuisineTypesBusiness.addCuisineType(CuisineTypesExamples.CUISINE_TYPE_MULTI_LANGUAGE_BO);
            fail("Need throw an exception");
        } catch (ApiException e) {
            assertEquals( messages.get("error.badrequest.duplicatedentityname.code"), e.getCode());
            assertEquals( messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"), e.getDescription());
        }

    }

    @Test
    public void deleteCuisineType() {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_MULTI_LANGUAGE_BO.getId())).thenReturn(CuisineTypesExamples.CUISINE_TYPE_PO_01);

        try {
            cuisineTypesBusiness.deleteCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID);
        } catch (NotFoundException e) {
            fail("Need to delete the resource");
        }
    }

    @Test
    public void deleteNotFoundCuisineType()  {
        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_MULTI_LANGUAGE_BO.getId())).thenReturn(null);
        try{
            cuisineTypesBusiness.deleteCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID);
            fail("Need throw an exception");
        } catch (NotFoundException e) {
            assertEquals( messages.get("error.notfound.code"), e.getCode());
            assertEquals( messages.get("error.notfound.desc"), e.getDescription());
        }

    }


}
