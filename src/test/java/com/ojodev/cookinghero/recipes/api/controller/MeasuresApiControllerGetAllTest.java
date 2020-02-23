package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.MeasuresBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.MeasuresExamples;
import com.ojodev.cookinghero.recipes.domain.model.DescriptiveNameBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeasuresApiControllerGetAllTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Messages messages;

    @MockBean
    private MeasuresBusiness measuresBusiness;

    private static final String LOCALE_ENGLISH = "en";
    private static final String LOCALE_SPANISH = "es";

    private static final String LOCALE_MULTIPLE_LANGUAGES = "en,de";

    private static final String INVALID_LANGUAGE = "xx";


    @Test
    public void getAllMeasures() throws Exception {

        MeasureBO measureBO01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureBO measureBO02 = new MeasureBO(MeasuresExamples.MEASURE_02_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresBusiness.getMeasures(any())).thenReturn(Arrays.asList(measureBO01, measureBO02));

        this.mvc.perform(get("/measures")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(MeasuresExamples.MEASURE_01_ID)))
                .andExpect(jsonPath("$[0].name.singular", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$[0].name.plural", is(MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL)))
                .andExpect(jsonPath("$[1].id", is(MeasuresExamples.MEASURE_02_ID)))
                .andExpect(jsonPath("$[1].name.singular", is(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR)))
                .andExpect(jsonPath("$[1].name.plural", is(MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL)));
    }


    @Test
    public void getAllMeasuresDifferentLanguages() throws Exception {

        MeasureBO measureBOEn = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureBO measureBOEs = new MeasureBO(MeasuresExamples.MEASURE_02_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_SPANISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_SPANISH_PLURAL, LanguageEnumBO.ES));

        when(this.measuresBusiness.getMeasures(eq(LanguageEnumBO.EN))).thenReturn(Arrays.asList(measureBOEn));
        when(this.measuresBusiness.getMeasures(eq(LanguageEnumBO.ES))).thenReturn(Arrays.asList(measureBOEs));

        this.mvc.perform(get("/measures")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_SPANISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_SPANISH))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].id", is(MeasuresExamples.MEASURE_02_ID)))
                .andExpect(jsonPath("$[0].name.singular", is(MeasuresExamples.MEASURE_02_NAME_SPANISH_SINGULAR)))
                .andExpect(jsonPath("$[0].name.plural", is(MeasuresExamples.MEASURE_02_NAME_SPANISH_PLURAL)));
    }

    @Test
    public void getAllMeasuresMultipleLanguages() throws Exception {

        MeasureBO measureBO01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureBO measureBO02 = new MeasureBO(MeasuresExamples.MEASURE_02_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresBusiness.getMeasures(any())).thenReturn(Arrays.asList(measureBO01, measureBO02));

        this.mvc.perform(get("/measures")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_MULTIPLE_LANGUAGES)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH));
    }

    @Test
    public void getAllMeasuresNoLanguage() throws Exception {

        MeasureBO measureBO01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureBO measureBO02 = new MeasureBO(MeasuresExamples.MEASURE_02_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresBusiness.getMeasures(any())).thenReturn(Arrays.asList(measureBO01, measureBO02));

        this.mvc.perform(get("/measures")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //TODO DMS Meter interceptor general para que ponga las cabeceras correctas
//                .andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparamrequired.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is(messages.get("error.badrequest.invalidparams.fields.headerparamrequired.desc", HttpHeaders.ACCEPT_LANGUAGE))));

    }

    @Test
    public void getAllMeasuresInvalidLanguage() throws Exception {

        MeasureBO measureBO01 = new MeasureBO(MeasuresExamples.MEASURE_01_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_01_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_01_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));
        MeasureBO measureBO02 = new MeasureBO(MeasuresExamples.MEASURE_02_ID,  new DescriptiveNameBO(MeasuresExamples.MEASURE_02_NAME_ENGLISH_SINGULAR, MeasuresExamples.MEASURE_02_NAME_ENGLISH_PLURAL, LanguageEnumBO.EN));

        when(this.measuresBusiness.getMeasures(any())).thenReturn(Arrays.asList(measureBO01, measureBO02));

        this.mvc.perform(get("/measures")
                .header(HttpHeaders.ACCEPT_LANGUAGE, INVALID_LANGUAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                //TODO DMS Meter interceptor general para que ponga las cabeceras correctas
                //.andExpect(header().string(HttpHeaders.CONTENT_LANGUAGE, LOCALE_ENGLISH))
                .andExpect(jsonPath("$.code", is(messages.get("error.badrequest.invalidparams.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.badrequest.invalidparams.desc"))))
                .andExpect(jsonPath("$.fields[0].code", is(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"))))
                .andExpect(jsonPath("$.fields[0].field", is(HttpHeaders.ACCEPT_LANGUAGE)))
                .andExpect(jsonPath("$.fields[0].description", is( messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum", HttpHeaders.ACCEPT_LANGUAGE, LanguageEnum.getValueList()))));

    }

    @Test
    public void getMeasuresOutOfMemoryException() throws Exception {

        when(this.measuresBusiness.getMeasures(any())).thenThrow(new OutOfMemoryError());

        this.mvc.perform(get("/measures")
                .header(HttpHeaders.ACCEPT_LANGUAGE, LOCALE_ENGLISH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
                .andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
    }



}
