package com.ojodev.cookinghero.recipes.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ojodev.cookinghero.recipes.api.model.Measure;
import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.data.CuisineTypesExamples;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresMapperTest {

    @Autowired
    private MeasuresMapper measuresMapper;

    @Autowired
    private FileUtils fileUtils;


    @Test
    public void convertMeasureBOToMeasure() {

        MeasureBO originMeasure = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        Measure resultMeasure = measuresMapper.toMeasure(originMeasure);
        assertNotNull(resultMeasure);
        assertEquals(MeasuresExamples.MEASURE_01_ID, resultMeasure.getId());
        assertNotNull(resultMeasure.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, resultMeasure.getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, resultMeasure.getName().getPlural());
    }


    @Test
    public void convertMeasuresBOListToMeasuresList() {

        MeasureBO originMeasure01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureBO originMeasure02 = new MeasureBO(MeasuresExamples.MEASURE_02_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        List<Measure> resultMeasure = measuresMapper.toMeasuresList(Arrays.asList(originMeasure01, originMeasure02));
        assertNotNull(resultMeasure);
        assertEquals(2, resultMeasure.size());
        assertEquals(MeasuresExamples.MEASURE_01_ID, resultMeasure.get(0).getId());
        assertNotNull(resultMeasure.get(0).getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, resultMeasure.get(0).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, resultMeasure.get(0).getName().getPlural());
        assertEquals(MeasuresExamples.MEASURE_02_ID, resultMeasure.get(1).getId());
        assertNotNull(resultMeasure.get(1).getName());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, resultMeasure.get(1).getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, resultMeasure.get(1).getName().getPlural());
    }

    @Test
    public void convertMeasurePOToMeasureBO() {

        MeasurePO originMeasurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        MeasureBO resultMeasure = measuresMapper.toMeasureBO(originMeasurePO, LanguageEnumBO.EN);
        assertNotNull(resultMeasure);
        assertEquals(MeasuresExamples.MEASURE_01_ID,resultMeasure.getId());
        assertNotNull(resultMeasure.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, resultMeasure.getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, resultMeasure.getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_EN, resultMeasure.getName().getLanguage().toString());

        MeasureBO measureEsBO = measuresMapper.toMeasureBO(originMeasurePO, LanguageEnumBO.ES);
        assertNotNull(measureEsBO);
        assertEquals(MeasuresExamples.MEASURE_01_ID,measureEsBO.getId());
        assertNotNull(measureEsBO.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measureEsBO.getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measureEsBO.getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ES, measureEsBO.getName().getLanguage().toString());

    }
}
