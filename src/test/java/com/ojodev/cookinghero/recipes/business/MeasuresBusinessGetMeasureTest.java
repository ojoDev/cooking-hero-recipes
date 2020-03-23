package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.MeasuresRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresBusinessGetMeasureTest {

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private MeasuresRepository measuresRepository;


    @Test
    public void getMeasureById() {

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
    public void getMeasureDifferentLanguages() {

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
    public void getMeasureByIdNotFound() {
        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(null);

        Optional<MeasureBO> MeasureEn = measuresBusiness.getMeasure(MeasuresExamples.MEASURE_01_ID, LanguageEnumBO.EN);
        assertNotNull(MeasureEn);
        assertFalse(MeasureEn.isPresent());
    }

}
