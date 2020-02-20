package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.business.MeasuresBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.FieldError;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
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

    @MockBean
    private MeasuresBusiness measuresBusiness;


    @Test
    public void patchMeasureNoDefaultLanguageComplete() throws Exception {

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.of(MeasuresExamples.MEASURE_BO_01_SPANISH));

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(MeasuresExamples.MEASURE_UPDATE_COMPLETE)))
                .andExpect(status().isNoContent());
    }

    @Test
    @Disabled
    public void patchMeasureNoDefaultLanguagePartial() throws Exception {

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.of(MeasuresExamples.MEASURE_BO_01_SPANISH));

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(MeasuresExamples.MEASURE_UPDATE_PARTIAL)))
                .andExpect(status().isNoContent());
    }


    @Test
    @Disabled
    public void patchMeasureDefaultLanguage() throws Exception {

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.of(MeasuresExamples.MEASURE_BO_01_ENGLISH));

        ApiFieldsException exception = new ApiFieldsException(
                messages.get("error.badrequest.invalidparams.code"),
                messages.get("error.badrequest.invalidparams.desc"),
                Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"),
                        HttpHeaders.ACCEPT_LANGUAGE,
                        messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage")))
        );

        doThrow(exception).when(measuresBusiness).addOrReplaceMeasure(any());

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(MeasuresExamples.MEASURE_UPDATE_COMPLETE)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage"))));
    }


    @Test
    @Disabled
    public void patchMeasureNotFound() throws Exception {

        when(this.measuresBusiness.getMeasure(any(), any())).thenReturn(Optional.empty());

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(MeasuresExamples.MEASURE_UPDATE_COMPLETE)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.desc"))));
    }



}