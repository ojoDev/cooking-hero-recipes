package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureMultiLanguageBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.DescriptiveNamePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import com.ojodev.cookinghero.recipes.infrastructure.repository.MeasuresRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresBusinessAddMeasureTest {

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private MeasuresRepository measuresRepository;

    @Test
    public void addNewMeasure() {

        MeasureMultiLanguageBO measureMultiLanguageBO = new MeasureMultiLanguageBO.Builder(Arrays.asList(
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN),
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.EN)),
                LanguageEnumBO.EN).build();

        when(this.measuresRepository.findByObjectId(measureMultiLanguageBO.getId())).thenReturn(null);
        when(this.measuresRepository.save(any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addMeasure(measureMultiLanguageBO));

        verify(measuresRepository).save(any(MeasurePO.class));
    }

    @Test
    public void addExistentMeasure() {

        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        MeasureMultiLanguageBO measureMultiLanguageBO = new MeasureMultiLanguageBO.Builder(Arrays.asList(
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN),
                new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.EN)),
                LanguageEnumBO.EN).build();

        when(this.measuresRepository.findByObjectId(measureMultiLanguageBO.getId())).thenReturn(Arrays.asList(measurePO));

        ApiException e = Assertions.assertThrows(ApiException.class, () -> {
            measuresBusiness.addMeasure(measureMultiLanguageBO);
        });

        assertEquals(messages.get("error.badrequest.duplicatedentityname.code"), e.getCode());
        assertEquals(messages.get("error.badrequest.duplicatedentityname.desc", "measure"), e.getDescription());

        verify(measuresRepository, never()).save(any(MeasurePO.class));
    }
}
