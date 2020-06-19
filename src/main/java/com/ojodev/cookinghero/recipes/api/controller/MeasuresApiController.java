package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.api.model.Measure;
import com.ojodev.cookinghero.recipes.api.model.MeasureNew;
import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import com.ojodev.cookinghero.recipes.business.MeasuresBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.config.RecipesConfig;
import com.ojodev.cookinghero.recipes.domain.constants.RecipesConstants;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureBO;
import com.ojodev.cookinghero.recipes.domain.model.MeasureMultiLanguageBO;
import com.ojodev.cookinghero.recipes.mapper.LanguageEnumMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresMultipleLanguageMapper;
import com.ojodev.cookinghero.recipes.mapper.MeasuresPatchMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Api(tags = "measures", description = "Measures used with ingredients")
public class MeasuresApiController implements MeasuresApi {

    @Autowired
    private MeasuresBusiness measuresBusiness;

    @Autowired
    private MeasuresMapper measuresMapper;

    @Autowired
    private MeasuresMultipleLanguageMapper measuresMultiLanguageMapper;

    @Autowired
    private MeasuresPatchMapper measuresPatchMapper;

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
                                              @ApiParam( value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage) throws NotFoundException, ApiFieldsException {
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        MeasureBO measureBO = measuresBusiness.getMeasure(measureId, language).orElseThrow(() -> new NotFoundException(messages.get("error.notfound.measure.code"),messages.get("error.notfound.measure.desc")));

        return ResponseEntity.status(HttpStatus.OK)
                .header(org.springframework.http.HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(measuresMapper.toMeasure(measureBO));
    }

    public ResponseEntity<Void> updateMeasure(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                              @ApiParam(value = "Measure id.", required = true, example = "tablespoon") @PathVariable("measure-id") String measureId,
                                              @ApiParam(value = "Measure to update.") @Valid @RequestBody MeasureUpdate body) throws ApiException {
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        throwErrorIfMeasureNotExists(measureId);
        Optional<MeasureBO> measureOrigin = measuresBusiness.getMeasure(measureId, language);
        if (measureOrigin.isPresent() && measureOrigin.get().getName().getLanguage() == language) {
            MeasureBO measurePatched = measuresPatchMapper.patch(measureOrigin.get(), body);
            measuresBusiness.addOrReplaceMeasure(measurePatched);
        } else {
            measuresBusiness.addOrReplaceMeasure(measuresMapper.toMeasureBO(body, measureId, language));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void throwErrorIfMeasureNotExists(String measureId) throws NotFoundException {
        measuresBusiness.getMeasure(measureId, RecipesConstants.DEFAULT_LANGUAGE).orElseThrow(() -> new NotFoundException(messages.get("error.notfound.measure.code"),messages.get("error.notfound.measure.desc")));
    }

    public ResponseEntity<Void> deleteMeasure(@ApiParam(value = "Measure id.", required = true, example = "tablespoon") @PathVariable("measure-id") String measureId) throws NotFoundException {
        measuresBusiness.deleteMeasure(measureId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private LanguageEnumBO checkAndExtractAcceptedLanguage(String acceptLanguage) throws ApiFieldsException {

        for (String language : acceptLanguage.split(RecipesConstants.ACCEPT_LANGUAGE_SEPARATOR)) {
            if (LanguageEnum.fromValue(language) != null) {
                return LanguageEnumBO.fromValue(language);
            }
        }
        throw new ApiFieldsException(messages.get("error.badrequest.invalidparams.code"), messages.get("error.badrequest.invalidparams.desc"),
                Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE, messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum", org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE, LanguageEnum.getValueList()))));
    }

    private void validateBody(MeasureNew body) throws ApiException {
        validateDefaultLanguage(body);
        validateInvalidLanguage(body);
    }

    private void validateDefaultLanguage(MeasureNew body) throws ApiException {
        boolean existsDefaultLanguage = body.getNames().stream().filter(name -> RecipesConstants.DEFAULT_LANGUAGE.equals(languageEnumMapper.toLanguageEnumBO(name.getLanguage()))).count() > 0;
        if (!existsDefaultLanguage) {
            throw new ApiBadRequestException(messages.get("error.badrequest.mustcontaindefault.code"), messages.get("error.badrequest.mustcontaindefault.desc", RecipesConstants.DEFAULT_LANGUAGE));
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



