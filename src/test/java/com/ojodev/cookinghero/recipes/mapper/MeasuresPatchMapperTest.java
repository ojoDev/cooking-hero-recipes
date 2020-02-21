package com.ojodev.cookinghero.recipes.mapper;

import com.ojodev.cookinghero.recipes.api.model.DescriptiveNameUpdate;
import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresPatchMapperTest {

    @Autowired
    private MeasuresPatchMapper measuresPatchMapper;

    @Autowired
    private FileUtils fileUtils;


    @Test
    public void convertPatchBodyComplete() {
        MeasureBO originMeasure = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureUpdate patchContent = new MeasureUpdate(new DescriptiveNameUpdate(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED));

        MeasureBO result = measuresPatchMapper.patch(originMeasure, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch change plural field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED, result.getName().getPlural());
    }

    @Test
    public void convertPatchBodyPartial() {

        MeasureBO originMeasure = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureUpdate patchContent = initPartialMeasureUpdate();

        MeasureBO result = measuresPatchMapper.patch(originMeasure, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertEquals("Patch not change plural field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, result.getName().getPlural());
    }

    @Test
    public void convertPatchBodyPartialAndNull() {

        MeasureBO originMeasure = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureUpdate patchContent = initPartialAndNullMeasureUpdate();

        MeasureBO result = measuresPatchMapper.patch(originMeasure, patchContent);

        assertNotNull(result);
        assertNotNull(result.getName());
        assertEquals("Language not change", LanguageEnumBO.EN, result.getName().getLanguage());
        assertEquals("Patch change singular field", MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, result.getName().getSingular());
        assertNull("Patch set plural field to null", result.getName().getPlural());
    }


    private static MeasureUpdate initPartialMeasureUpdate() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED);
        return new MeasureUpdate(descriptiveNameUpdate);
    }

    private static MeasureUpdate initPartialAndNullMeasureUpdate() {
        DescriptiveNameUpdate descriptiveNameUpdate = new DescriptiveNameUpdate();
        descriptiveNameUpdate.setSingular(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED);
        descriptiveNameUpdate.setPlural(null);
        return new MeasureUpdate(descriptiveNameUpdate);
    }
}
