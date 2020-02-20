package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.DescriptiveName;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.api.model.MeasureNew;
import com.ojodev.cookinghero.recipes.api.model.MeasureNewName;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresMultipleLanguageMapperTest {

    @Autowired
    private MeasuresMultipleLanguageMapper measuresMultipleLanguageMapper;

    @Test
    public void convertMeasureNewToMeasureMultiLanguageBO() {

        MeasureNew measureNewOrigin = new MeasureNew(Arrays.asList(
                new MeasureNewName(new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL), LanguageEnum.EN),
                new MeasureNewName(new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)));

        MeasureMultiLanguageBO measureMultiLanguageBO = measuresMultipleLanguageMapper.toMeasureMultiLanguageBO(measureNewOrigin, LanguageEnumBO.EN);

        assertNotNull(measureMultiLanguageBO);
        assertNotNull(measureMultiLanguageBO.getNames());
        assertEquals(2, measureMultiLanguageBO.getNames().size());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureMultiLanguageBO.getId());
        assertEquals(LanguageEnumBO.EN, measureMultiLanguageBO.getNames().get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureMultiLanguageBO.getNames().get(0).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measureMultiLanguageBO.getNames().get(0).getPlural());
        assertEquals(LanguageEnumBO.ES, measureMultiLanguageBO.getNames().get(1).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measureMultiLanguageBO.getNames().get(1).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measureMultiLanguageBO.getNames().get(1).getPlural());

    }

    @Test
    public void convertMeasureMultiLanguageBOToMeasurePO() {
        DescriptiveNameBO descriptiveNameEnglish = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO descriptiveNameSpanish = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);
        MeasureMultiLanguageBO measureMultiLanguageBO = new MeasureMultiLanguageBO.Builder(Arrays.asList(
                descriptiveNameEnglish, descriptiveNameSpanish), LanguageEnumBO.EN).build();

        MeasurePO measurePO = measuresMultipleLanguageMapper.toMeasurePO(measureMultiLanguageBO);

        assertNotNull(measurePO);
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measurePO.getObjectId());
        assertNotNull(measurePO.getNames());
        assertEquals(2, measurePO.getNames().size());
        assertEquals(MeasuresExamples.LANGUAGE_EN, measurePO.getNames().get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measurePO.getNames().get(0).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measurePO.getNames().get(0).getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ES, measurePO.getNames().get(1).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measurePO.getNames().get(1).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measurePO.getNames().get(1).getPlural());
    }
}
