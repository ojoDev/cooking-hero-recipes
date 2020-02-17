package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Measure;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresMapperTest {

    @Autowired
    private MeasuresMapper measuresMapper;


    @Test
    public void convertMeasureBOToMeasure() {
        Measure measure = measuresMapper.toMeasure(MeasuresExamples.MEASURE_BO_01_ENGLISH);
        assertNotNull(measure);
        assertEquals(MeasuresExamples.MEASURE_01_ID, measure.getId());
        assertNotNull(measure.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measure.getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measure.getName().getPlural());
    }


    @Test
    public void convertMeasuresBOListToMeasuresList() {
        List<Measure> measures = measuresMapper.toMeasuresList(Arrays.asList(MeasuresExamples.MEASURE_BO_01_ENGLISH, MeasuresExamples.MEASURE_BO_02_ENGLISH));
        assertNotNull(measures);
        assertEquals(2, measures.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, measures.get(0).getId());
        assertNotNull(measures.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measures.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measures.get(0).getName().getPlural());
        assertEquals(MeasuresExamples.MEASURE_02_ID, measures.get(1).getId());
        assertNotNull(measures.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, measures.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, measures.get(1).getName().getPlural());
    }

    @Test
    public void convertMeasurePOToMeasureBO() {
        MeasureBO measureEnBO = measuresMapper.toMeasureBO(MeasuresExamples.MEASURE_PO_01, LanguageEnumBO.EN);
        assertNotNull(measureEnBO);
        assertEquals(MeasuresExamples.MEASURE_01_ID,measureEnBO.getId());
        assertNotNull(measureEnBO.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureEnBO.getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measureEnBO.getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_EN, measureEnBO.getName().getLanguage());

        MeasureBO measureEsBO = measuresMapper.toMeasureBO(MeasuresExamples.MEASURE_PO_01, LanguageEnumBO.ES);
        assertNotNull(measureEsBO);
        assertEquals(MeasuresExamples.MEASURE_01_ID,measureEsBO.getId());
        assertNotNull(measureEsBO.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, measureEsBO.getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, measureEsBO.getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_EN, measureEsBO.getName().getLanguage());

    }
  


}
