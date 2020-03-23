package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.LanguageNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.CuisineTypesRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CuisineTypesBusinessDeleteCuisineTypeTest {

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private CuisineTypesRepository cuisineTypesRepository;


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


}
