package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasureMultiLanguageBOTest {


    @Test
    public void createMeasureMultiLanguageBOWithDefaultLanguage() {
        List<DescriptiveNameBO> names = new ArrayList<>();
        names.add(MeasuresExamples.MEASURE_01_NAME_ENGLISH);
        MeasureMultiLanguageBO measure = new MeasureMultiLanguageBO.Builder(
                names,
                MeasuresExamples.LANGUAGE_ENUM_ENGLISH)
                .build();

        assertNotNull(measure);
        assertNotNull(measure.getNames());
        assertEquals(1, measure.getNames().size());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getLanguage(), measure.getNames().get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getSingular(), measure.getNames().get(0).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getPlural(), measure.getNames().get(0).getPlural());

    }

    @Test
    public void createMeasureMultiLanguageBOMultipleLanguages() {
        List<DescriptiveNameBO> names = new ArrayList<>();
        names.add(MeasuresExamples.MEASURE_01_NAME_ENGLISH);

        MeasureMultiLanguageBO measure = new MeasureMultiLanguageBO.Builder(
                names,
                MeasuresExamples.LANGUAGE_ENUM_ENGLISH)
                .name(MeasuresExamples.MEASURE_01_NAME_SPANISH)
                .build();

        assertNotNull(measure);
        assertEquals(2, measure.getNames().size());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getLanguage(), measure.getNames().get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getSingular(), measure.getNames().get(0).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH.getPlural(), measure.getNames().get(0).getPlural());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH.getLanguage(), measure.getNames().get(1).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH.getSingular(), measure.getNames().get(1).getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH.getPlural(), measure.getNames().get(1).getPlural());
    }

    @Test
    public void createMeasureMultiLanguageBOMultipleLanguagesWithReplace() {
        List<DescriptiveNameBO> names = new ArrayList<>();
        names.add(MeasuresExamples.MEASURE_01_NAME_ENGLISH);

        MeasureMultiLanguageBO measure = new MeasureMultiLanguageBO.Builder(
                names,
                MeasuresExamples.LANGUAGE_ENUM_ENGLISH)
                .name(MeasuresExamples.MEASURE_01_NAME_SPANISH)
                .name(MeasuresExamples.MEASURE_02_NAME_ENGLISH)
                .name(MeasuresExamples.MEASURE_02_NAME_SPANISH)
                .build();

        assertNotNull(measure);
        assertEquals(2, measure.getNames().size());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH.getLanguage(), measure.getNames().get(0).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH.getSingular(), measure.getNames().get(0).getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_ENGLISH.getPlural(), measure.getNames().get(0).getPlural());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_SPANISH.getLanguage(), measure.getNames().get(1).getLanguage());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_SPANISH.getSingular(), measure.getNames().get(1).getSingular());
        assertEquals(MeasuresExamples.MEASURE_02_NAME_SPANISH.getPlural(), measure.getNames().get(1).getPlural());
    }


    @Test
    public void createMeasureMultiLanguageBOWithNoDefaultLanguage() {
        List<DescriptiveNameBO> names = new ArrayList<>();
        names.add(MeasuresExamples.MEASURE_01_NAME_SPANISH);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    new MeasureMultiLanguageBO.Builder(
                            names,
                            MeasuresExamples.LANGUAGE_ENUM_ENGLISH)
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
                            MeasuresExamples.LANGUAGE_ENUM_ENGLISH)
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
                            MeasuresExamples.LANGUAGE_ENUM_ENGLISH)
                            .build();
                }, "Need to be throw an exception");
        assertEquals("Measure needs to include at least one name and language fields", exception.getMessage());
    }

}
