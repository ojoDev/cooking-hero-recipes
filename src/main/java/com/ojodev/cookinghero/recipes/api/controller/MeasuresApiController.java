package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.api.model.Measure;
import com.ojodev.cookinghero.recipes.api.model.MeasureNew;
import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.business.MeasuresBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.config.RecipesConfig;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.FieldError;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.mapper.LanguageEnumMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Api(tags = "measures", description = "Measures used with ingredients")
public class MeasuresApiController implements MeasuresApi {

    private static final String ACCEPT_LANGUAGE_SEPARATOR = ",";

    @Autowired
    private MeasuresMapper measuresMapper;

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private LanguageEnumMapper languageEnumMapper;

    @Autowired
    private Messages messages;

    @Autowired
    private RecipesConfig config;

    public ResponseEntity<List<Measure>> getMeasures(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage) throws ApiFieldsException {

        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        return ResponseEntity.status(HttpStatus.OK)
                .header(org.springframework.http.HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(measuresMapper.toMeasuresList(measuresBusiness.getMeasures(language)));
    }

    public ResponseEntity<Void> addMeasure(@ApiParam(value = "Measure to add.") @Valid @RequestBody MeasureNew body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Measure> getMeasure(@ApiParam(value = "Measure id.", required = true, example = "tablespoon") @PathVariable("measure-id") String measureId,
                                              @ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage) throws NotFoundException, ApiFieldsException {
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        MeasureBO measureBO = measuresBusiness.getMeasure(measureId, language).orElseThrow(NotFoundException::new);

        return ResponseEntity.status(HttpStatus.OK)
                .header(org.springframework.http.HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(measuresMapper.toMeasure(measureBO));
    }

    public ResponseEntity<Void> updateMeasure(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                              @ApiParam(value = "Measure id.", required = true, example = "tablespoon") @PathVariable("measure-id") String measureId,
                                              @ApiParam(value = "Measure to update.") @Valid @RequestBody MeasureUpdate body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteMeasure(@ApiParam(value = "Measure id.", required = true, example = "tablespoon") @PathVariable("measure-id") String measureId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    private LanguageEnumBO checkAndExtractAcceptedLanguage(String acceptLanguage) throws ApiFieldsException {

        for (String language : acceptLanguage.split(ACCEPT_LANGUAGE_SEPARATOR)) {
            if (LanguageEnum.fromValue(language) != null){
                return LanguageEnumBO.fromValue(language);
            }
        }
        throw new ApiFieldsException(messages.get("error.badrequest.invalidparams.code"), messages.get("error.badrequest.invalidparams.desc"),
                Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE, org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE + " " + messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum") + " " + LanguageEnum.getValueList())));
    }
}
