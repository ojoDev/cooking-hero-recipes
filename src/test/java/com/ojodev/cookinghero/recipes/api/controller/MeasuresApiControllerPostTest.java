package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.DescriptiveName;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.api.model.MeasureNew;
import com.ojodev.cookinghero.recipes.api.model.MeasureNewName;
import com.ojodev.cookinghero.recipes.business.MeasuresBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.FileNameEnum;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.exception.ApiBadRequestException;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.utils.FileUtils;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeasuresApiControllerPostTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @Autowired
    private FileUtils fileUtils;

    @MockBean
    private MeasuresBusiness measuresBusiness;


    private static final String LOCALE_ENGLISH = "en";


    @Test
    public void postMeasure() throws Exception {

        MeasureNew measureNew = new MeasureNew(Arrays.asList(
                new MeasureNewName(new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL), LanguageEnum.EN),
                new MeasureNewName(new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)));

        this.mvc.perform(post("/measures")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(measureNew)))
                .andExpect(header().string(HttpHeaders.LOCATION, endsWith(String.format("/measures/%s", MeasuresExamples.MEASURE_01_ID))))
                .andExpect(status().isCreated());

        verify(measuresBusiness).addMeasure(any());
    }

    @Test
    public void postNotDefaultMeasure() throws Exception {

        MeasureNew measureNoDefaultLanguage = new MeasureNew(Arrays.asList(
                new MeasureNewName(new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)));


        this.mvc.perform(post("/measures")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(measureNoDefaultLanguage)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.mustcontaindefault.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.mustcontaindefault.desc", LOCALE_ENGLISH))));

        verify(measuresBusiness, never()).addMeasure(any());
    }

    @Test
    public void postInvalidLanguageInMeasureName() throws Exception {

        this.mvc.perform(post("/measures")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(fileUtils.fileAsJsonString(FileNameEnum.MEASURE_INVALID_LANGUAGE)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidlanguage.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidlanguage.desc", LanguageEnumBO.getValueList()))));

        verify(measuresBusiness, never()).addMeasure(any());
    }

    @Test
    public void postDuplicateMeasure() throws Exception {

        MeasureNew measureNew = new MeasureNew(Arrays.asList(
                new MeasureNewName(new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL), LanguageEnum.EN),
                new MeasureNewName(new DescriptiveName(MeasuresExamples.MEASURE_01_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_SPANISH_PLURAL), LanguageEnum.ES)));

        doThrow(new ApiBadRequestException(messages.get("error.badrequest.duplicatedentityname.code"), messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"))).when(measuresBusiness).addMeasure(any());

        this.mvc.perform(post("/measures")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .content(TestUtils.asJsonString(measureNew)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.duplicatedentityname.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.duplicatedentityname.desc", "cuisine type"))));

        verify(measuresBusiness).addMeasure(any());
    }

}