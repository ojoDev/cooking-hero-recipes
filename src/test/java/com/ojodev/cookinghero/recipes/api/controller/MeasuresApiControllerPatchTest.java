
package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.DescriptiveNameUpdate;
import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.business.MeasuresBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeasuresApiControllerPatchTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private MeasuresBusiness measuresBusiness;


    @Test
    public void patchMeasureComplete() throws Exception {

        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_02_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.of(measureBOEs));

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(fileUtils.fileAsJsonString(FileNameEnum.MEASURE_PATCH_COMPLETE)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<MeasureBO> argumentMeasure = ArgumentCaptor.forClass(MeasureBO.class);
        verify(measuresBusiness).addOrReplaceMeasure(argumentMeasure.capture());
        assertNotNull(argumentMeasure.getValue().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, argumentMeasure.getValue().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED, argumentMeasure.getValue().getName().getPlural());
    }

    @Test
    public void patchMeasurePartial() throws Exception {

        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.of(measureBOEs));

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(fileUtils.fileAsJsonString(FileNameEnum.MEASURE_PATCH_PARTIAL)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<MeasureBO> argumentMeasure = ArgumentCaptor.forClass(MeasureBO.class);
        verify(measuresBusiness).addOrReplaceMeasure(argumentMeasure.capture());
        assertNotNull(argumentMeasure.getValue().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, argumentMeasure.getValue().getName().getSingular());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, argumentMeasure.getValue().getName().getPlural());
    }

    @Test
    public void patchMeasurePartialNullValue() throws Exception {

        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_01_ID, new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.of(measureBOEs));

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(fileUtils.fileAsJsonString(FileNameEnum.MEASURE_PATCH_PARTIAL_AND_NULL)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<MeasureBO> argumentMeasure = ArgumentCaptor.forClass(MeasureBO.class);
        verify(measuresBusiness).addOrReplaceMeasure(argumentMeasure.capture());
        assertNotNull(argumentMeasure.getValue().getName());
        assertEquals(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, argumentMeasure.getValue().getName().getSingular());
        assertNull(argumentMeasure.getValue().getName().getPlural());
    }


    @Test
    public void patchMeasureNotFound() throws Exception {

        MeasureUpdate measureUpdateComplete = new MeasureUpdate(new DescriptiveNameUpdate(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR_CHANGED, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL_CHANGED));

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.empty());

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(measureUpdateComplete)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.measure.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.measure.desc"))));

        verify(measuresBusiness, never()).addOrReplaceMeasure(any());

    }


}