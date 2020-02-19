package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.model.MeasureMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresMultipleLanguageMapperTest {

    @Autowired
    private MeasuresMultipleLanguageMapper measuresMultipleLanguageMapper;

    @Test
    public void convertMeasureNewToMeasureMultiLanguageBO() {
        MeasureMultiLanguageBO measureMultiLanguageBO = measuresMultipleLanguageMapper.toMeasureMultiLanguageBO(MeasuresExamples.MEASURE_NEW, MeasuresExamples.LANGUAGE_ENUM_ENGLISH);
        assertNotNull(measureMultiLanguageBO);
        assertNotNull(measureMultiLanguageBO.getNames());
        assertEquals(2, measureMultiLanguageBO.getNames().size());
        assertEquals(MeasuresExamples.MEASURE_NEW.getNames().get(0).getName().getSingular(), measureMultiLanguageBO.getId());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_ENGLISH, measureMultiLanguageBO.getNames().get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_NEW.getNames().get(0).getName().getSingular(), measureMultiLanguageBO.getNames().get(0).getSingular());
        assertEquals(MeasuresExamples.MEASURE_NEW.getNames().get(0).getName().getPlural(), measureMultiLanguageBO.getNames().get(0).getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ENUM_SPANISH, measureMultiLanguageBO.getNames().get(1).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_NEW.getNames().get(1).getName().getSingular(), measureMultiLanguageBO.getNames().get(1).getSingular());
        assertEquals(MeasuresExamples.MEASURE_NEW.getNames().get(1).getName().getPlural(), measureMultiLanguageBO.getNames().get(1).getPlural());

    }

    @Test
    public void convertMeasureMultiLanguageBOToMeasurePO() {
        MeasurePO measurePO = measuresMultipleLanguageMapper.toMeasurePO(MeasuresExamples.MEASURE_MULTI_LANGUAGE_BO);
        assertNotNull(measurePO);
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getSingular(), measurePO.getObjectId());
        assertNotNull(measurePO.getNames());
        assertEquals(2, measurePO.getNames().size());
        assertEquals(MeasuresExamples.LANGUAGE_EN,measurePO.getNames().get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getSingular(), measurePO.getNames().get(0).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getPlural(), measurePO.getNames().get(0).getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ES,measurePO.getNames().get(1).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH.getSingular(), measurePO.getNames().get(1).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH.getPlural(), measurePO.getNames().get(1).getPlural());
    }
}
