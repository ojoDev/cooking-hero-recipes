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
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
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
        assertEquals(MeasuresExamples.LANGUAGE_EN, measureEnBO.getName().getLanguage().toString());

        MeasureBO measureEsBO = measuresMapper.toMeasureBO(MeasuresExamples.MEASURE_PO_01, LanguageEnumBO.ES);
        assertNotNull(measureEsBO);
        assertEquals(MeasuresExamples.MEASURE_01_ID,measureEsBO.getId());
        assertNotNull(measureEsBO.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, measureEsBO.getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, measureEsBO.getName().getPlural());
        assertEquals(MeasuresExamples.LANGUAGE_ES, measureEsBO.getName().getLanguage().toString());

    }

  /*  @Test
    public void convertPatchBodyComplete() throws ApiException {
        MeasureBO origin = MeasuresExamples.MEASURE_BO_01_ENGLISH;
        Map<String, Object> content = fileUtils.fileAsMap(FileNameEnum.MEASURE_PATCH_COMPLETE);
        MeasureBO result = measuresMapper.patch(origin, content);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular(), "Patch change singular field");
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED, result.getName().getPlural(), "Patch change plural field");
    }

    @Test
    public void convertPatchBodyPartial() throws ApiException {
        MeasureBO origin = MeasuresExamples.MEASURE_BO_01_ENGLISH;
        Map<String, Object> content = fileUtils.fileAsMap(FileNameEnum.MEASURE_PATCH_PARTIAL);
        MeasureBO result = measuresMapper.patch(origin, content);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular(), "Patch change singular field");
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, result.getName().getPlural(), "Patch not change plural field");
    }

    @Test
    public void convertPatchBodyPartialAndNull() throws ApiException {
        MeasureBO origin = MeasuresExamples.MEASURE_BO_01_ENGLISH;
        MeasureUpdate content = fileUtils.fileAsBean(MeasureUpdate.class, FileNameEnum.MEASURE_PATCH_PARTIAL_AND_NULL);
        MeasureBO result = measuresMapper.patch(origin, content);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular(), "Patch change singular field");
        assertEquals(null, result.getName().getPlural(), "Patch change plural to null");
    }
*/
}
