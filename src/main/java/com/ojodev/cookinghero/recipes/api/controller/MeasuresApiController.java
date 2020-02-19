package com.ojodev.cookinghero.recipes.api.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.business.MeasuresBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.config.RecipesConfig;
import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import com.ojodev.cookinghero.recipes.domain.model.*;
import com.ojodev.cookinghero.recipes.mapper.LanguageEnumMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresMultipleLanguageMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@Controller
@Api(tags = "measures", description = "Measures used with ingredients")
public class MeasuresApiController implements MeasuresApi {

    private static final String ACCEPT_LANGUAGE_SEPARATOR = ",";


    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private MeasuresMapper measuresMapper;

    @Autowired
    private MeasuresMultipleLanguageMapper measuresMultiLanguageMapper;

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

    public ResponseEntity<Void> addMeasure(@ApiParam(value = "Measure to add.") @Valid @RequestBody MeasureNew body) throws ApiException {
        validateBody(body);
        String id = saveMeasure(body);
        return ResponseEntity.created(generateLocationHeader(id)).build();
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
                                              @ApiParam(value = "Measure to update.") @Valid @RequestBody Map<Object, Object> body) throws ApiFieldsException {
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);
        //TODO DMS: Esta mal, hacer patrón para PATCH
        //((HashMap<String, String>)body.get("name")).get("singular")


        // measuresBusiness.addOrReplaceMeasure(new MeasureBO(measureId, body.getName(), language));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteMeasure(@ApiParam(value = "Measure id.", required = true, example = "tablespoon") @PathVariable("measure-id") String measureId) throws NotFoundException {
        measuresBusiness.deleteMeasure(measureId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private LanguageEnumBO checkAndExtractAcceptedLanguage(String acceptLanguage) throws ApiFieldsException {

        for (String language : acceptLanguage.split(ACCEPT_LANGUAGE_SEPARATOR)) {
            if (LanguageEnum.fromValue(language) != null) {
                return LanguageEnumBO.fromValue(language);
            }
        }
        throw new ApiFieldsException(messages.get("error.badrequest.invalidparams.code"), messages.get("error.badrequest.invalidparams.desc"),
                Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE, org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE + " " + messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum") + " " + LanguageEnum.getValueList())));
    }

    private void validateBody(MeasureNew body) throws ApiException {
        validateDefaultLanguage(body);
        validateInvalidLanguage(body);
    }

    private void validateDefaultLanguage(MeasureNew body) throws ApiException {
        boolean existsDefaultLanguage = body.getNames().stream().filter(name -> RecipeConstants.DEFAULT_LANGUAGE.equals(languageEnumMapper.toLanguageEnumBO(name.getLanguage()))).count() > 0;
        if (!existsDefaultLanguage) {
            throw new ApiBadRequestException(messages.get("error.badrequest.mustcontaindefault.code"), messages.get("error.badrequest.mustcontaindefault.desc", RecipeConstants.DEFAULT_LANGUAGE));
        }
    }

    private void validateInvalidLanguage(MeasureNew body) throws ApiBadRequestException {
        boolean invalidLanguage = body.getNames().stream().filter(name -> name.getLanguage() == null).findAny().isPresent();
        if (invalidLanguage) {
            throw new ApiBadRequestException(messages.get("error.badrequest.invalidlanguage.code"),
                    messages.get("error.badrequest.invalidlanguage.desc", LanguageEnumBO.getValueList()));
        }
    }

    /**
     * Save measure
     *
     * @param measureNew new measure
     * @return id of new measure
     * @throws ApiException exception
     */
    private String saveMeasure(MeasureNew measureNew) throws ApiException {
        MeasureMultiLanguageBO measureMultiLanguageBO = measuresMultiLanguageMapper.toMeasureMultiLanguageBO(measureNew, config.getDefaultLanguage());
        measuresBusiness.addMeasure(measureMultiLanguageBO);
        return measureMultiLanguageBO.getId();
    }


    private URI generateLocationHeader(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}



