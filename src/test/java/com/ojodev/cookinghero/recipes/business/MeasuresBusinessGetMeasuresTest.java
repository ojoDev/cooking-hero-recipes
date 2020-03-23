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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresBusinessGetMeasuresTest {

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private MeasuresRepository measuresRepository;

    @Test
    public void getAllMeasuresByLanguage() {

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
    public void getAllMeasuresWithDefaultLanguage() {

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


}
