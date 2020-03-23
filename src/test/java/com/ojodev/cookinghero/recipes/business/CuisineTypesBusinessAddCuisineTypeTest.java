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
public class CuisineTypesBusinessAddCuisineTypeTest {

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesRepository cuisineTypesRepository;

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


}
