package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
import com.ojodev.cookinghero.recipes.infrastructure.repository.MeasuresRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeasuresBusinessTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @MockBean
    private MeasuresRepository measuresRepository;

    @Test
    public void getAllMeasuresByLanguage() throws Exception {

        when(this.measuresRepository.findAll()).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01, MeasuresExamples.MEASURE_PO_02));

        List<MeasureBO> measuresEn = measuresBusiness.getMeasures(LanguageEnumBO.EN);

        assertNotNull(measuresEn);
        assertEquals(3, measuresEn.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measuresEn.get(0).getId());
        assertNotNull(measuresEn.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measuresEn.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measuresEn.get(0).getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, measuresEn.get(0).getName().getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_ID, measuresEn.get(1).getId());
        assertNotNull(measuresEn.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, measuresEn.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, measuresEn.get(1).getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, measuresEn.get(1).getName().getLanguage());

        List<MeasureBO> measuresEs = measuresBusiness.getMeasures(LanguageEnumBO.ES);

        assertNotNull(measuresEs);
        assertEquals(3, measuresEs.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measuresEs.get(0).getId());
        assertNotNull(measuresEs.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measuresEs.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measuresEs.get(0).getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, measuresEs.get(0).getName().getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_ID, measuresEs.get(1).getId());
        assertNotNull(measuresEs.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, measuresEs.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, measuresEs.get(1).getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, measuresEs.get(1).getName().getLanguage());
    }

/*
    @Test
    public void getAllCuisineTypesByNameAndLanguage() throws Exception {
        when(this.cuisineTypesRepository.findByName(MeasuresExamples.MEASURE_01_NAME_ENGLISH, MeasuresExamples.LANGUAGE_EN)).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));
        when(this.cuisineTypesRepository.findByName(MeasuresExamples.MEASURE_02_NAME_ENGLISH, MeasuresExamples.LANGUAGE_EN)).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_02));
        when(this.cuisineTypesRepository.findByName(MeasuresExamples.MEASURE_01_NAME_SPANISH, MeasuresExamples.LANGUAGE_ES)).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));

        List<CuisineTypeBO> cuisineTypes01English = cuisineTypesBusiness.getCuisineTypes(MeasuresExamples.MEASURE_01_NAME_ENGLISH, LanguageEnumBO.EN);
        assertEquals(1, cuisineTypes01English.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, cuisineTypes01English.get(0).getId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH, cuisineTypes01English.get(0).getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypes01English.get(0).getLanguage());

        List<CuisineTypeBO> cuisineTypes02English = cuisineTypesBusiness.getCuisineTypes(MeasuresExamples.MEASURE_02_NAME_ENGLISH, LanguageEnumBO.EN);
        assertEquals(1, cuisineTypes02English.size());
        assertEquals(MeasuresExamples.MEASURE_02_ID, cuisineTypes02English.get(0).getId());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH, cuisineTypes02English.get(0).getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypes02English.get(0).getLanguage());

        List<CuisineTypeBO> cuisineTypes01Spanish = cuisineTypesBusiness.getCuisineTypes(MeasuresExamples.MEASURE_01_NAME_SPANISH, LanguageEnumBO.ES);
        assertEquals(1, cuisineTypes01Spanish.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, cuisineTypes01Spanish.get(0).getId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH, cuisineTypes01Spanish.get(0).getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_SPANISH, cuisineTypes01Spanish.get(0).getLanguage());
    }


    @Test
    public void getAllCuisineTypesWithDefaultLanguage() throws Exception {
        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01, MeasuresExamples.MEASURE_PO_02, MeasuresExamples.MEASURE_PO_03));

        List<CuisineTypeBO> cuisineTypesEn = cuisineTypesBusiness.getCuisineTypes(null);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, cuisineTypesEn.get(0).getId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_ID, cuisineTypesEn.get(1).getId());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH, cuisineTypesEn.get(1).getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(1).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_03_ID, cuisineTypesEn.get(2).getId());
        assertEquals(MeasuresExamples.MEASURE_03_NAME_ENGLISH, cuisineTypesEn.get(2).getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesEn.get(2).getLanguage());
    }

    @Test
    public void getAllCuisineTypesByNullName() throws Exception {
        when(this.cuisineTypesRepository.findByName(any(), eq(MeasuresExamples.LANGUAGE_EN))).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_03));
        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01, MeasuresExamples.MEASURE_PO_02, MeasuresExamples.MEASURE_PO_03));

        List<CuisineTypeBO> cuisineTypesNullName = cuisineTypesBusiness.getCuisineTypes(null, LanguageEnumBO.EN);
        List<CuisineTypeBO> cuisineTypesNoName = cuisineTypesBusiness.getCuisineTypes(LanguageEnumBO.EN);

        assertEquals(3, cuisineTypesNullName.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, cuisineTypesNullName.get(0).getId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH, cuisineTypesNullName.get(0).getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypesNullName.get(0).getLanguage());

        assertEquals(3, cuisineTypesNoName.size());
        assertEquals(cuisineTypesNoName.get(0).getId(), cuisineTypesNoName.get(0).getId());
        assertEquals(cuisineTypesNoName.get(0).getName(), cuisineTypesNoName.get(0).getName());
        assertEquals(cuisineTypesNoName.get(0).getLanguage(), cuisineTypesNoName.get(0).getLanguage());

    }

    @Test
    public void getCuisineTypeById() throws Exception {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_01_ID)).thenReturn(MeasuresExamples.MEASURE_PO_01);
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_02_ID)).thenReturn(MeasuresExamples.MEASURE_PO_02);

        Optional<CuisineTypeBO> cuisineType01 = cuisineTypesBusiness.getCuisineType(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineType01);
        assertTrue(cuisineType01.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, cuisineType01.get().getId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH, cuisineType01.get().getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineType01.get().getLanguage());

        Optional<CuisineTypeBO> cuisineType02 = cuisineTypesBusiness.getCuisineType(MeasuresExamples.MEASURE_02_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineType02);
        assertTrue(cuisineType02.isPresent());
        assertEquals(MeasuresExamples.MEASURE_02_ID, cuisineType02.get().getId());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH, cuisineType02.get().getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineType02.get().getLanguage());
    }

    @Test
    public void getCuisineTypeByIdDifferentLanguages() throws Exception {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_01_ID)).thenReturn(MeasuresExamples.MEASURE_PO_01);

        Optional<CuisineTypeBO> cuisineTypeEn = cuisineTypesBusiness.getCuisineType(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineTypeEn);
        assertTrue(cuisineTypeEn.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, cuisineTypeEn.get().getId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH, cuisineTypeEn.get().getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, cuisineTypeEn.get().getLanguage());

        Optional<CuisineTypeBO> cuisineTypeEs = cuisineTypesBusiness.getCuisineType(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.ES);
        assertNotNull(cuisineTypeEs);
        assertTrue(cuisineTypeEs.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, cuisineTypeEs.get().getId());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH, cuisineTypeEs.get().getName());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_SPANISH, cuisineTypeEs.get().getLanguage());
    }

    @Test
    public void getCuisineTypeByIdNotFound() throws Exception {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_01_ID)).thenReturn(null);

        Optional<CuisineTypeBO> cuisineTypeEn = cuisineTypesBusiness.getCuisineType(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(cuisineTypeEn);
        assertFalse(cuisineTypeEn.isPresent());
    }

    @Test
    public void addNewCuisineTypes() throws Exception {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO.getId())).thenReturn(null);
        when(this.cuisineTypesRepository.save(any())).thenReturn(null);

        cuisineTypesBusiness.addCuisineType(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO);
    }

    @Test
    public void addExistentCuisineTypes() {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO.getId())).thenReturn(MeasuresExamples.MEASURE_PO_01);

        ApiException e = Assertions.assertThrows(ApiException.class, () -> {
            cuisineTypesBusiness.addCuisineType(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO);
        });

        assertEquals(messages.get("error.badrequest.duplicatedentityname.code"), e.getCode());
        assertEquals(messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"), e.getDescription());
    }

    @Test()
    @DisplayName("Replace cuisine type name if name exists")
    public void addOrReplaceCuisineTypeReplaceNoDefaultLanguage()  {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(MeasuresExamples.MEASURE_PO_01);

        Assertions.assertDoesNotThrow(() -> cuisineTypesBusiness.addOrReplaceCuisineType(MeasuresExamples.MEASURE_BO_01_SPANISH));

    }

    @Test()
    @DisplayName("Add cuisine type name if name exists")
    public void addOrReplaceCuisineTypeAddNoDefaultLanguage()  {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(MeasuresExamples.MEASURE_PO_01_ONLY_ENGLISH);

        Assertions.assertDoesNotThrow(() -> cuisineTypesBusiness.addOrReplaceCuisineType(MeasuresExamples.MEASURE_BO_01_SPANISH));

    }

    @Test
    public void addOrReplaceCuisineTypeDefaultLanguage()  {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(MeasuresExamples.MEASURE_PO_01);

        ApiFieldsException e = Assertions.assertThrows(ApiFieldsException.class, () -> {
            cuisineTypesBusiness.addOrReplaceCuisineType(MeasuresExamples.MEASURE_BO_01_ENGLISH);
        });
        assertEquals( messages.get("error.badrequest.invalidparams.code"), e.getCode());
        assertEquals( messages.get("error.badrequest.invalidparams.desc"), e.getDescription());
        assertNotNull(e.getFields());
        assertEquals(1, e.getFields().size());
        assertEquals( messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), e.getFields().get(0).getCode());
        assertEquals( messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage"), e.getFields().get(0).getDescription());
    }

    @Test
    public void addOrReplaceCuisineTypeNoExists()  {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> cuisineTypesBusiness.addOrReplaceCuisineType(MeasuresExamples.MEASURE_BO_01_ENGLISH));
    }

    @Test
    public void deleteCuisineType() {
        when(this.cuisineTypesRepository.findById(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO.getId())).thenReturn(MeasuresExamples.MEASURE_PO_01);

        Assertions.assertDoesNotThrow(() -> cuisineTypesBusiness.deleteCuisineType(MeasuresExamples.MEASURE_01_ID),"Need to delete the resource");
    }

    @Test
    public void deleteNotFoundCuisineType()  {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            cuisineTypesBusiness.deleteCuisineType(MeasuresExamples.MEASURE_01_ID);
        });
        assertEquals( messages.get("error.notfound.code"), exception.getCode());
        assertEquals( messages.get("error.notfound.desc"), exception.getDescription());
    }
*/


}
