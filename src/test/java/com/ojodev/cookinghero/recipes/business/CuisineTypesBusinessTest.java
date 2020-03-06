package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageNameBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesBusinessTest {

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

    @Test
    public void addNewCuisineTypes() {

        CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO = new CuisineTypeMultiLanguageBO.Builder(Arrays.asList(
                new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN),
                new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES)),
                LanguageEnumBO.EN).build();

        when(this.cuisineTypesRepository.findById(cuisineTypeMultiLanguageBO.getId())).thenReturn(null);
        when(this.cuisineTypesRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> cuisineTypesBusiness.addCuisineType(cuisineTypeMultiLanguageBO));

        verify(cuisineTypesRepository).save(any(CuisineTypePO.class));
    }

    @Test
    public void addExistentCuisineTypes() {

        CuisineTypePO cuisineTypePO = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO = new CuisineTypeMultiLanguageBO.Builder(Arrays.asList(
                new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN),
                new LanguageNameBO(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES)),
                LanguageEnumBO.EN).build();
        
        when(this.cuisineTypesRepository.findById(cuisineTypeMultiLanguageBO.getId())).thenReturn(cuisineTypePO);

        ApiException e = Assertions.assertThrows(ApiException.class, () -> {
            cuisineTypesBusiness.addCuisineType(cuisineTypeMultiLanguageBO);
        });
        assertEquals(messages.get("error.badrequest.duplicatedentityname.code"), e.getCode());
        assertEquals(messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"), e.getDescription());

        verify(cuisineTypesRepository, never()).save(any(CuisineTypePO.class));
    }

    @Test
    @DisplayName("Replace cuisine type name if name exists")
    public void addOrReplaceCuisineTypeReplace() {

        CuisineTypePO cuisineTypePO = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));
        CuisineTypeBO cuisineTypeEs = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);

        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(cuisineTypePO);

        Assertions.assertDoesNotThrow(() -> cuisineTypesBusiness.addOrReplaceCuisineType(cuisineTypeEs));

        verify(cuisineTypesRepository).save(any(CuisineTypePO.class));

    }

    @Test
    @DisplayName("Add cuisine type name if name exists")
    public void addOrReplaceCuisineTypeAddLanguage() {

        CuisineTypePO cuisineTypeOnlyEnglish = initCuisineTypeNewOnlyEnglish();
        CuisineTypeBO cuisineTypeEs = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnumBO.ES);

        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(cuisineTypeOnlyEnglish);

        Assertions.assertDoesNotThrow(() -> cuisineTypesBusiness.addOrReplaceCuisineType(cuisineTypeEs));

        verify(cuisineTypesRepository).save(any(CuisineTypePO.class));

    }

    @Test
    public void addOrReplaceCuisineTypeNoExists() {

        CuisineTypeBO cuisineTypeBO = new CuisineTypeBO(CuisineTypesExamples.CUISINE_TYPE_01_ID, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnumBO.EN);

        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> cuisineTypesBusiness.addOrReplaceCuisineType(cuisineTypeBO));

        verify(cuisineTypesRepository, never()).save(any(CuisineTypePO.class));
    }

    @Test
    public void deleteCuisineType() {

        CuisineTypePO cuisineTypePO = new CuisineTypePO(CuisineTypesExamples.CUISINE_TYPE_01_ID, Arrays.asList(
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH),
                new LanguageNamePO(CuisineTypesExamples.LANGUAGE_ES, CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)));

        when(this.cuisineTypesRepository.findById(CuisineTypesExamples.CUISINE_TYPE_01_ID)).thenReturn(cuisineTypePO);

        Assertions.assertDoesNotThrow(() -> cuisineTypesBusiness.deleteCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID), "Need to delete the resource");

        verify(cuisineTypesRepository).deleteById(CuisineTypesExamples.CUISINE_TYPE_01_ID);
    }

    @Test
    public void deleteNotFoundCuisineType() {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            cuisineTypesBusiness.deleteCuisineType(CuisineTypesExamples.CUISINE_TYPE_01_ID);
        });
        assertEquals(messages.get("error.notfound.cuisinetype.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.cuisinetype.desc"), exception.getDescription());

        verify(cuisineTypesRepository, never()).deleteById(anyString());
    }

    private static CuisineTypePO initCuisineTypeNewOnlyEnglish() {
        CuisineTypePO cuisineTypePO = new CuisineTypePO();
        cuisineTypePO.setObjectId(CuisineTypesExamples.CUISINE_TYPE_01_ID);
        ArrayList<LanguageNamePO> names = new ArrayList<>();
        names.add(new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH));
        cuisineTypePO.setNames(names);
        return cuisineTypePO;
    }

}
