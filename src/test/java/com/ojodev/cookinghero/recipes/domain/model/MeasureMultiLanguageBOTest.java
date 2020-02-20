package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureMultiLanguageBOTest {


    @Test
    public void createMeasureMultiLanguageBOWithDefaultLanguage() {
        DescriptiveNameBO measureNameEnglish = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        List<DescriptiveNameBO> initialNames = Arrays.asList(measureNameEnglish);

        MeasureMultiLanguageBO measure = new MeasureMultiLanguageBO.Builder(
                initialNames,
                LanguageEnumBO.EN)
                .build();

        assertNotNull(measure);
        assertNotNull(measure.getNames());
        assertEquals(1, measure.getNames().size());
        assertEquals(measureNameEnglish.getLanguage(), measure.getNames().get(0).getLanguage());
        assertEquals(measureNameEnglish.getSingular(), measure.getNames().get(0).getSingular());
        assertEquals(measureNameEnglish.getPlural(), measure.getNames().get(0).getPlural());

    }

    @Test
    public void createMeasureMultiLanguageBOMultipleLanguages() {
        DescriptiveNameBO measureNameEnglish = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO measureNameSpanish = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);

        List<DescriptiveNameBO> initialNames = Arrays.asList(measureNameEnglish);

        MeasureMultiLanguageBO measure = new MeasureMultiLanguageBO.Builder(
                initialNames,
                LanguageEnumBO.EN)
                .name(measureNameSpanish)
                .build();

        assertNotNull(measure);
        assertEquals(2, measure.getNames().size());
        assertEquals(measureNameEnglish.getLanguage(), measure.getNames().get(0).getLanguage());
        assertEquals(measureNameEnglish.getSingular(), measure.getNames().get(0).getSingular());
        assertEquals(measureNameEnglish.getPlural(), measure.getNames().get(0).getPlural());
        assertEquals(measureNameSpanish.getLanguage(), measure.getNames().get(1).getLanguage());
        assertEquals(measureNameSpanish.getSingular(), measure.getNames().get(1).getSingular());
        assertEquals(measureNameSpanish.getPlural(), measure.getNames().get(1).getPlural());
    }

    @Test
    public void createMeasureMultiLanguageBOMultipleLanguagesWithReplace() {
        DescriptiveNameBO measureNameEnglish01 = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO measureNameSpanish01 = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);
        DescriptiveNameBO measureNameEnglish02 = new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN);
        DescriptiveNameBO measureNameSpanish02 = new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);

        List<DescriptiveNameBO> initialNames = Arrays.asList(measureNameEnglish01);

        MeasureMultiLanguageBO measure = new MeasureMultiLanguageBO.Builder(
                initialNames,
                LanguageEnumBO.EN)
                .name(measureNameSpanish01)
                .name(measureNameEnglish02)
                .name(measureNameSpanish02)
                .build();

        assertNotNull(measure);
        assertEquals(2, measure.getNames().size());
        assertEquals(measureNameEnglish02.getLanguage(), measure.getNames().get(0).getLanguage());
        assertEquals(measureNameEnglish02.getSingular(), measure.getNames().get(0).getSingular());
        assertEquals(measureNameEnglish02.getPlural(), measure.getNames().get(0).getPlural());
        assertEquals(measureNameSpanish02.getLanguage(), measure.getNames().get(1).getLanguage());
        assertEquals(measureNameSpanish02.getSingular(), measure.getNames().get(1).getSingular());
        assertEquals(measureNameSpanish02.getPlural(), measure.getNames().get(1).getPlural());
    }


    @Test
    public void createMeasureMultiLanguageBOWithNoDefaultLanguage() {
        DescriptiveNameBO measureNameSpanish = new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES);
        List<DescriptiveNameBO> initialNames = Arrays.asList(measureNameSpanish);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new MeasureMultiLanguageBO.Builder(
                            initialNames,
                            LanguageEnumBO.EN)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Measure needs to include default language name: en", exception.getMessage());
    }

    @Test
    public void createMeasureMultiLanguageBOWithInvalidFields() {
        List<DescriptiveNameBO> names = new ArrayList<>();
        names.add(new DescriptiveNameBO(null,null,null));

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new MeasureMultiLanguageBO.Builder(
                            names,
                            LanguageEnumBO.EN)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Names needs to include singular and plural fields", exception.getMessage());
    }

    @Test
    public void createMeasureMultiLanguageBONoNames() {
        List<DescriptiveNameBO> names = new ArrayList<>();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new MeasureMultiLanguageBO.Builder(
                            names,
                            LanguageEnumBO.EN)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Measure needs to include at least one name and language fields", exception.getMessage());
    }

}
