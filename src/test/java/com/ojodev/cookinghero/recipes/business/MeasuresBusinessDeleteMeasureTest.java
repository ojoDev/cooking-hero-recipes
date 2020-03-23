package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
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

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresBusinessDeleteMeasureTest {

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private MeasuresRepository measuresRepository;

    @Test
    public void deleteMeasure() {
        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findByObjectId(measurePO.getObjectId())).thenReturn(Arrays.asList(measurePO));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.deleteMeasure(MeasuresExamples.MEASURE_01_ID), "Need to delete the resource");

        verify(measuresRepository).deleteById(MeasuresExamples.MEASURE_01_ID);
    }

    @Test
    public void deleteNotFoundMeasure() {
        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            measuresBusiness.deleteMeasure(MeasuresExamples.MEASURE_01_ID);
        });
        assertEquals(messages.get("error.notfound.measure.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.measure.desc"), exception.getDescription());

        verify(measuresRepository, never()).deleteById(anyString());
    }

}
