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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
    @Disabled
    public void patchMeasureNoDefaultLanguage() throws Exception {

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(MeasuresExamples.MEASURE_UPDATE_ES)))
                .andExpect(status().isNoContent());
        }

    @Test
    @Disabled
    public void patchMeasureDefaultLanguage() throws Exception {
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
                .content(TestUtils.asJsonString(MeasuresExamples.MEASURE_UPDATE_EN)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is( messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.nodefaultlanguage"))));
    }


    @Test
    @Disabled
    public void patchMeasureNotFound() throws Exception {

        doThrow(new NotFoundException()).when(measuresBusiness).addOrReplaceMeasure(any());

        this.mvc.perform(patch("/measures/{measure-id}", MeasuresExamples.MEASURE_01_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, MeasuresExamples.LANGUAGE_ES)
                .content(TestUtils.asJsonString(MeasuresExamples.MEASURE_UPDATE_ES)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code", is(messages.get("error.notfound.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.notfound.desc"))));
    }

}