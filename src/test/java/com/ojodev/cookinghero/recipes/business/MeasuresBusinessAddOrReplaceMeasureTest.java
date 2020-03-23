package com.ojodev.cookinghero.recipes.business;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasuresBusinessAddOrReplaceMeasureTest {

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private Messages messages;

    @MockBean
    private MeasuresRepository measuresRepository;

    @Test()
    public void addOrReplaceMeasureReplace() {

        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        MeasurePO measurePO = new MeasurePO(MeasuresExamples.MEASURE_01_ID, Arrays.asList(
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN),
                new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, MeasuresExamples.LANGUAGE_ES)));

        when(this.measuresRepository.findByObjectId(measurePO.getObjectId())).thenReturn(Arrays.asList(measurePO));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addOrReplaceMeasure(measureBOEs));

        verify(measuresRepository).save(any(MeasurePO.class));
    }

    @Test()
    public void addOrReplaceMeasureAddLanguage() {

        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));
        MeasurePO measurePOOnlyEnglish = initMeasureOnlyEnglish();

        when(this.measuresRepository.findByObjectId(measurePOOnlyEnglish.getObjectId())).thenReturn(Arrays.asList(measurePOOnlyEnglish));

        Assertions.assertDoesNotThrow(() -> measuresBusiness.addOrReplaceMeasure(measureBOEs));

        verify(measuresRepository).save(any(MeasurePO.class));
    }

    @Test
    public void addOrReplaceMeasureNoExists() {
        MeasureBO measureBO = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresRepository.findByObjectId(MeasuresExamples.MEASURE_01_ID)).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> measuresBusiness.addOrReplaceMeasure(measureBO));
        assertEquals(messages.get("error.notfound.measure.code"), exception.getCode());
        assertEquals(messages.get("error.notfound.measure.desc"), exception.getDescription());

        verify(measuresRepository, never()).save(any(MeasurePO.class));
    }

    private static MeasurePO initMeasureOnlyEnglish() {
        MeasurePO measurePO = new MeasurePO();
        measurePO.setObjectId(MeasuresExamples.MEASURE_01_ID);
        ArrayList<DescriptiveNamePO> names = new ArrayList<>();
        names.add(new DescriptiveNamePO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, MeasuresExamples.LANGUAGE_EN));
        measurePO.setNames(names);
        return measurePO;
    }

}
