package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
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

import java.util.ArrayList;
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

        MeasurePO measurePO01 = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));
        MeasurePO measurePO02 = new MeasurePO(MeasuresExamples.MEASURE_02_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_02_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findAll()).thenReturn(Arrays.asList(measurePO01, measurePO02));

        List<MeasureBO> measuresEn = measuresBusiness.getMeasures(LanguageEnumBO.EN);

        assertNotNull(measuresEn);
        assertEquals(2, measuresEn.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measuresEn.get(0).getId());
        assertNotNull(measuresEn.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measuresEn.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measuresEn.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, measuresEn.get(0).getName().getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_ID, measuresEn.get(1).getId());
        assertNotNull(measuresEn.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, measuresEn.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, measuresEn.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, measuresEn.get(1).getName().getLanguage());

        List<MeasureBO> measuresEs = measuresBusiness.getMeasures(LanguageEnumBO.ES);

        assertNotNull(measuresEs);
        assertEquals(2, measuresEs.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measuresEs.get(0).getId());
        assertNotNull(measuresEs.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measuresEs.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measuresEs.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.ES, measuresEs.get(0).getName().getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_ID, measuresEs.get(1).getId());
        assertNotNull(measuresEs.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_SPANISH_SINGULAR, measuresEs.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_SPANISH_PLURAL, measuresEs.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.ES, measuresEs.get(1).getName().getLanguage());
    }

    @Test
    public void getAllMeasuresWithDefaultLanguage() throws Exception {

        MeasurePO measurePO01 = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));
        MeasurePO measurePO02 = new MeasurePO(MeasuresExamples.MEASURE_02_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_02_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findAll()).thenReturn(Arrays.asList(measurePO01, measurePO02));

        List<MeasureBO> measuresEn = measuresBusiness.getMeasures(null);

        assertNotNull(measuresEn);
        assertEquals(2, measuresEn.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measuresEn.get(0).getId());
        assertNotNull(measuresEn.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measuresEn.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measuresEn.get(0).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, measuresEn.get(0).getName().getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_ID, measuresEn.get(1).getId());
        assertNotNull(measuresEn.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, measuresEn.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, measuresEn.get(1).getName().getPlural());
        assertEquals(LanguageEnumBO.EN, measuresEn.get(1).getName().getLanguage());
    }

    @Test
    public void getMeasureById() throws Exception {

        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(Arrays.asList(measurePO));

        Optional<MeasureBO> measureEn = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);

        assertNotNull(measureEn);
        assertTrue(measureEn.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measureEn.get().getId());
        assertNotNull(measureEn.get().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureEn.get().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measureEn.get().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, measureEn.get().getName().getLanguage());
    }

    @Test
    public void getMeasureDifferentLanguages() throws Exception {

        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(Arrays.asList(measurePO));

        Optional<MeasureBO> measureEn = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(measureEn);
        assertTrue(measureEn.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measureEn.get().getId());
        assertNotNull(measureEn.get().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureEn.get().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measureEn.get().getName().getPlural());
        assertEquals(LanguageEnumBO.EN, measureEn.get().getName().getLanguage());

        Optional<MeasureBO> measureEs = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.ES);
        assertNotNull(measureEs);
        assertTrue(measureEs.isPresent());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measureEs.get().getId());
        assertNotNull(measureEs.get().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measureEs.get().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measureEs.get().getName().getPlural());
        assertEquals(LanguageEnumBO.ES, measureEs.get().getName().getLanguage());
    }

    @Test
    public void getMeasureByIdNotFound() throws Exception {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(null);

        Optional<MeasureBO> MeasureEn = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(MeasureEn);
        assertFalse(MeasureEn.isPresent());
    }

    @Test
    public void addNewMeasures() {

        MeasureMultiLanguageBO measureMultiLanguageBO = new MeasureMultiLanguageBO.Builder(Arrays.asList(
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN),
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.EN)),
                LanguageEnumBO.EN).build();

        when(this.measuresRepository.findByObjectId(measureMultiLanguageBO.getId())).thenReturn(null);
        when(this.measuresRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addMeasure(measureMultiLanguageBO));
    }

    @Test
    public void addExistentMeasures() {

        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        MeasureMultiLanguageBO measureMultiLanguageBO = new MeasureMultiLanguageBO.Builder(Arrays.asList(
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN),
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.EN)),
                LanguageEnumBO.EN).build();

        when(this.measuresRepository.findByObjectId(measureMultiLanguageBO.getId())).thenReturn(Arrays.asList(measurePO));

        ApiException e = Assertions.assertThrows(ApiException.class, () -> {
            measuresBusiness.addMeasure(measureMultiLanguageBO);
        });

        assertEquals(messages.get("error.badrequest.duplicatedentityname.code"), e.getCode());
        assertEquals(messages.get("error.badrequest.duplicatedentityname.desc", "measure"), e.getDescription());
    }

    @Test()
    public void addOrReplaceMeasureReplaceNoDefaultLanguage() {

        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findByObjectId(measurePO.getObjectId())).thenReturn(Arrays.asList(measurePO));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addOrReplaceMeasure(measureBOEs));
    }

    @Test()
    public void addOrReplaceMeasureAddNoDefaultLanguage() {

        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        MeasurePO measurePOOnlyEnglish = initMeasureOnlyEnglish();

        when(this.measuresRepository.findByObjectId(measurePOOnlyEnglish.getObjectId())).thenReturn(Arrays.asList(measurePOOnlyEnglish));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addOrReplaceMeasure(measureBOEs));
    }

    @Test
    public void addOrReplaceMeasureDefaultLanguage() {

        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        MeasureBO measureBOEn = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresRepository.findByObjectId(measurePO.getObjectId())).thenReturn(Arrays.asList(measurePO));

        ApiFieldsException e = Assertions.assertThrows(ApiFieldsException.class, () -> {
            measuresBusiness.addOrReplaceMeasure(measureBOEn);
        });
        assertEquals(messages.get("error.badrequest.invalidparams.code"), e.getCode());
        assertEquals(messages.get("error.badrequest.invalidparams.desc"), e.getDescription());
        assertNotNull(e.getFields());
        assertEquals(1, e.getFields().size());
        assertEquals(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), e.getFields().get(0).getCode());
        assertEquals(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage"), e.getFields().get(0).getDescription());
    }

    @Test
    public void addOrReplaceMeasureNoExists() {
        MeasureBO measureBO = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> measuresBusiness.addOrReplaceMeasure(measureBO));
        assertEquals(messages.get("error.notfound.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.desc"), exception.getDescription());
    }

    @Test
    public void deleteMeasure() {
        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findByObjectId(measurePO.getObjectId())).thenReturn(Arrays.asList(measurePO));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.deleteMeasure(MeasuresExamples.MEASURE_01_ID), "Need to delete the resource");
    }

    @Test
    public void deleteNotFoundMeasure() {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            measuresBusiness.deleteMeasure(MeasuresExamples.MEASURE_01_ID);
        });
        assertEquals(messages.get("error.notfound.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.desc"), exception.getDescription());
    }

    private static MeasurePO initMeasureOnlyEnglish() {
        MeasurePO measurePO = new MeasurePO();
        measurePO.setObjectId(MeasuresExamples.MEASURE_01_ID);
        ArrayList<DescriptiveNamePO> names = new ArrayList<>();
        names.add(new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN));
        measurePO.setNames(names);
        return measurePO;
    }

}
