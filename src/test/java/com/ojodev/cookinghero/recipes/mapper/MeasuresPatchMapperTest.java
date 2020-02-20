package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.Measure;
import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresPatchMapperTest {

    @Autowired
    private MeasuresPatchMapper measuresPatchMapper;

    @Autowired
    private FileUtils fileUtils;


    @Test
    public void convertPatchBodyComplete() throws ApiException, FileNotFoundException {
        MeasureBO origin = MeasuresExamples.MEASURE_BO_01_ENGLISH;
        MeasureUpdate content = MeasuresExamples.MEASURE_UPDATE_COMPLETE;
        MeasureBO result = measuresPatchMapper.patch(origin, content);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch change plural field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED, result.getName().getPlural());
    }

    @Test
    public void convertPatchBodyPartial() throws ApiException {
        MeasureBO origin = MeasuresExamples.MEASURE_BO_01_ENGLISH;
        MeasureUpdate content = MeasuresExamples.MEASURE_UPDATE_PARTIAL;
        MeasureBO result = measuresPatchMapper.patch(origin, content);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch not change plural field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, result.getName().getPlural());
    }

    @Test
    public void convertPatchBodyPartialAndNull() throws ApiException {
        MeasureBO origin = MeasuresExamples.MEASURE_BO_01_ENGLISH;
        MeasureUpdate content = MeasuresExamples.MEASURE_UPDATE_PARTIAL_AND_NULL;
        MeasureBO result = measuresPatchMapper.patch(origin, content);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertNull("Patch set plural field to null", result.getName().getPlural());
    }

}
