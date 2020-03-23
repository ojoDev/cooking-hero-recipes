package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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
public class CuisineTypesBusinessAddOrReplaceCuisineTypeTest {

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesRepository cuisineTypesRepository;


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

    private static CuisineTypePO initCuisineTypeNewOnlyEnglish() {
        CuisineTypePO cuisineTypePO = new CuisineTypePO();
        cuisineTypePO.setObjectId(CuisineTypesExamples.CUISINE_TYPE_01_ID);
        ArrayList<LanguageNamePO> names = new ArrayList<>();
        names.add(new LanguageNamePO(CuisineTypesExamples.LANGUAGE_EN, CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH));
        cuisineTypePO.setNames(names);
        return cuisineTypePO;
    }

}
