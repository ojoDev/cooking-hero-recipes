package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
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
        assertEquals(2, measuresEn.size());
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
        assertEquals(2, measuresEs.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measuresEs.get(0).getId());
        assertNotNull(measuresEs.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measuresEs.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measuresEs.get(0).getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_SPANISH, measuresEs.get(0).getName().getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_ID, measuresEs.get(1).getId());
        assertNotNull(measuresEs.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_SPANISH_SINGULAR, measuresEs.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_SPANISH_PLURAL, measuresEs.get(1).getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_SPANISH, measuresEs.get(1).getName().getLanguage());
    }

    @Test
    public void getAllMeasuresWithDefaultLanguage() throws Exception {

        when(this.measuresRepository.findAll()).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01, MeasuresExamples.MEASURE_PO_02));

        List<MeasureBO> measuresEn = measuresBusiness.getMeasures(null);

        assertNotNull(measuresEn);
        assertEquals(2, measuresEn.size());
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
    }

    @Test
    public void getMeasureById() throws Exception {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));

        Optional<MeasureBO> measureEn = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);

        assertNotNull(measureEn);
        assertTrue(measureEn.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measureEn.get().getId());
        assertNotNull(measureEn.get().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureEn.get().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measureEn.get().getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, measureEn.get().getName().getLanguage());
    }

    @Test
    public void getMeasureDifferentLanguages() throws Exception {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));

        Optional<MeasureBO> measureEn = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(measureEn);
        assertTrue(measureEn.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measureEn.get().getId());
        assertNotNull(measureEn.get().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureEn.get().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measureEn.get().getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, measureEn.get().getName().getLanguage());

        Optional<MeasureBO> measureEs = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.ES);
        assertNotNull(measureEs);
        assertTrue(measureEs.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measureEs.get().getId());
        assertNotNull(measureEs.get().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measureEs.get().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measureEs.get().getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_SPANISH, measureEs.get().getName().getLanguage());
    }

    @Test
    public void getMeasureByIdNotFound() throws Exception {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(null);

        Optional<MeasureBO> MeasureEn = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(MeasureEn);
        assertFalse(MeasureEn.isPresent());
    }

    @Test
    public void addNewMeasures() throws Exception {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO.getId())).thenReturn(null);
        when(this.measuresRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addMeasure(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO));
    }

    @Test
    public void addExistentMeasures() {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO.getId())).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));

        ApiException e = Assertions.assertThrows(ApiException.class, () -> {
            measuresBusiness.addMeasure(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO);
        });

        assertEquals(messages.get("error.badrequest.duplicatedentityname.code"), e.getCode());
        assertEquals(messages.get("error.badrequest.duplicatedentityname.desc", "measure"), e.getDescription());
    }

    @Test()
    public void addOrReplaceMeasureReplaceNoDefaultLanguage()  {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addOrReplaceMeasure(MeasuresExamples.MEASURE_BO_01_SPANISH));
    }

    @Test()
    public void addOrReplaceMeasureAddNoDefaultLanguage()  {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01_ONLY_ENGLISH));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addOrReplaceMeasure(MeasuresExamples.MEASURE_BO_01_SPANISH));
    }

    @Test
    public void addOrReplaceMeasureDefaultLanguage()  {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));

        ApiFieldsException e = Assertions.assertThrows(ApiFieldsException.class, () -> {
            measuresBusiness.addOrReplaceMeasure(MeasuresExamples.MEASURE_BO_01_ENGLISH);
        });
        assertEquals( messages.get("error.badrequest.invalidparams.code"), e.getCode());
        assertEquals( messages.get("error.badrequest.invalidparams.desc"), e.getDescription());
        assertNotNull(e.getFields());
        assertEquals(1, e.getFields().size());
        assertEquals( messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), e.getFields().get(0).getCode());
        assertEquals( messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage"), e.getFields().get(0).getDescription());
    }

    @Test
    public void addOrReplaceMeasureNoExists()  {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_PO_01.getObjectId())).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> measuresBusiness.addOrReplaceMeasure(MeasuresExamples.MEASURE_BO_01_ENGLISH));
        assertEquals( messages.get("error.notfound.code"), exception.getCode());
        assertEquals( messages.get("error.notfound.desc"), exception.getDescription());
    }

    @Test
    public void deleteMeasure() {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO.getId())).thenReturn(Arrays.asList(MeasuresExamples.MEASURE_PO_01));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.deleteMeasure(MeasuresExamples.MEASURE_01_ID),"Need to delete the resource");
    }

    @Test
    public void deleteNotFoundMeasure()  {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            measuresBusiness.deleteMeasure(MeasuresExamples.MEASURE_01_ID);
        });
        assertEquals( messages.get("error.notfound.code"), exception.getCode());
        assertEquals( messages.get("error.notfound.desc"), exception.getDescription());
    }

}
