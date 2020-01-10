package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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

        List<CuisineTypeBO> cuisineTypesEn = cuisineTypesBusiness.getCuisineTypes(LanguageEnum.EN);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEn.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEn.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypesEn.get(1).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEn.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH, cuisineTypesEn.get(2).getName());

        List<CuisineTypeBO> cuisineTypesEs = cuisineTypesBusiness.getCuisineTypes(LanguageEnum.ES);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEs.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypesEs.get(0).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEs.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_SPANISH, cuisineTypesEs.get(1).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEs.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_SPANISH, cuisineTypesEs.get(2).getName());

    }

    @Test
    public void getAllCuisineTypesByNameAndLanguage() throws Exception {
        when(this.cuisineTypesRepository.findByNameENContaining(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH)).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_01));
        when(this.cuisineTypesRepository.findByNameENContaining(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH)).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_02));
        when(this.cuisineTypesRepository.findByNameESContaining(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH)).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_01));

        List<CuisineTypeBO> cuisineTypes01English = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, LanguageEnum.EN);
        assertEquals(1, cuisineTypes01English.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypes01English.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypes01English.get(0).getName());

        List<CuisineTypeBO> cuisineTypes02English = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, LanguageEnum.EN);
        assertEquals(1, cuisineTypes02English.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypes02English.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypes02English.get(0).getName());

        List<CuisineTypeBO> cuisineTypes01Spanish = cuisineTypesBusiness.getCuisineTypes(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, LanguageEnum.ES);
        assertEquals(1, cuisineTypes01Spanish.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypes01Spanish.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_SPANISH, cuisineTypes01Spanish.get(0).getName());
    }


    @Test
    public void getAllCuisineTypesWithDefaultLanguage() throws Exception {
        when(this.cuisineTypesRepository.findAll()).thenReturn(Arrays.asList(CuisineTypesExamples.CUISINE_TYPE_PO_01, CuisineTypesExamples.CUISINE_TYPE_PO_02, CuisineTypesExamples.CUISINE_TYPE_PO_03));

        List<CuisineTypeBO> cuisineTypesEn = cuisineTypesBusiness.getCuisineTypes(null);

        assertEquals(3, cuisineTypesEn.size());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_ID, cuisineTypesEn.get(0).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_01_NAME_ENGLISH, cuisineTypesEn.get(0).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_ID, cuisineTypesEn.get(1).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_02_NAME_ENGLISH, cuisineTypesEn.get(1).getName());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_ID, cuisineTypesEn.get(2).getId());
        assertEquals(CuisineTypesExamples.CUISINE_TYPE_03_NAME_ENGLISH, cuisineTypesEn.get(2).getName());

    }


}
