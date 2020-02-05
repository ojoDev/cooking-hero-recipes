package com.ojodev.cookinghero.recipes.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNewName;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.config.RecipesConfig;
import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMapper;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMultipleLanguageMapper;
import com.ojodev.cookinghero.recipes.mapper.LanguageEnumMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Api(tags = "cuisine-types", description = "Cuisine types API")
public class CuisineTypesApiController implements CuisineTypesApi {

    private static final String ACCEPT_LANGUAGE_SEPARATOR = ",";

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @Autowired
    private CuisineTypesMapper cuisineTypeMapper;

    @Autowired
    private CuisineTypesMultipleLanguageMapper cuisineTypesMultipleLanguageMapper;

    @Autowired
    private LanguageEnumMapper languageEnumMapper;

    @Autowired
    private Messages messages;

    @Autowired
    private RecipesConfig config;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public CuisineTypesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CuisineType>> getCuisineTypes(
            @ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage,
            @ApiParam(value = "Cuisine type name. Partial searches allowed.") @Valid @RequestParam(value = "name", required = false) String name) throws ApiException {

        checkAccept(request.getHeader(HttpHeaders.ACCEPT));
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        return  ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(cuisineTypeMapper.toCuisineTypeList(cuisineTypesBusiness.getCuisineTypes(name, language)));

    }

    public ResponseEntity<Void> addCuisineType(
            @ApiParam(value = "Cuisine type to add.") @Valid @RequestBody CuisineTypeNew body) throws ApiException {
        checkAccept(request.getHeader(HttpHeaders.ACCEPT));
        validateBody(body);
        saveCuisineType(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void validateBody(CuisineTypeNew body) throws ApiException {
        validateDefaultLanguage(body);
        validateInvalidLanguage(body);
    }

    private void validateDefaultLanguage(CuisineTypeNew body) throws ApiException{
        boolean existsDefaultLanguage = body.getNames().stream().filter(name -> RecipeConstants.DEFAULT_LANGUAGE.equals(languageEnumMapper.toLanguageEnumBO(name.getLanguage()))).count() > 0;
        if (!existsDefaultLanguage) {
            throw new ApiBadRequestException(messages.get("error.badrequest.mustcontaindefault.code"), messages.get("error.badrequest.mustcontaindefault.desc", RecipeConstants.DEFAULT_LANGUAGE));
        }
    }

    private void validateInvalidLanguage(CuisineTypeNew body) throws ApiBadRequestException {
        boolean invalidLanguage = body.getNames().stream().filter(name -> name.getLanguage() == null).findAny().isPresent();
        if (invalidLanguage) {
            throw new ApiBadRequestException(messages.get("error.badrequest.invalidlanguage.code"),
                    messages.get("error.badrequest.invalidlanguage.desc", LanguageEnumBO.getValueList()));
        }
    }


    private void saveCuisineType(CuisineTypeNew cuisineTypeNew) {
        cuisineTypesBusiness.addCuisineType(cuisineTypesMultipleLanguageMapper.toCuisineTypeMultiLanguageBO(cuisineTypeNew, config.getDefaultLanguage()));
        //TODO DMS Borrar si no se usa
        // saveCuisineTypeDefaultLanguage(cuisineTypeNew.getNames());
        // saveCuisineTypesNoDefaultLanguage(cuisineTypeNew.getNames());
    }

    //TODO DMS Borrar si no se usa
  /*  private void saveCuisineTypeDefaultLanguage(List<CuisineTypeNewName> names) {
       names.stream().filter(name -> RecipeConstants.DEFAULT_LANGUAGE.equals(languageEnumMapper.toLanguageEnumBO(name.getLanguage()))).forEach(cuisineTypeName ->
               cuisineTypesBusiness.addOrReplaceCuisineType(cuisineTypeMapper.toCuisineTypeBO(cuisineTypeName), languageEnumMapper.toLanguageEnumBO(cuisineTypeName.getLanguage())));
    }

    private void saveCuisineTypesNoDefaultLanguage(List<CuisineTypeNewName> names) {
        names.stream().filter(name -> !RecipeConstants.DEFAULT_LANGUAGE.equals(languageEnumMapper.toLanguageEnumBO(name.getLanguage()))).forEach(cuisineTypeName ->
                cuisineTypesBusiness.addOrReplaceCuisineType(cuisineTypeMapper.toCuisineTypeBO(cuisineTypeName),  languageEnumMapper.toLanguageEnumBO(cuisineTypeName.getLanguage())));
    }*/

    public ResponseEntity<CuisineType> getCuisineType(@ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId, @ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage) throws ApiException {

        checkAccept(request.getHeader(HttpHeaders.ACCEPT));
        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        Optional<CuisineTypeBO> cuisineTypeBOOpt = cuisineTypesBusiness.getCuisineType(cuisineTypeId, language);
        if (!cuisineTypeBOOpt.isPresent()) {
            throw new NotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(cuisineTypeMapper.toCuisineType(cuisineTypeBOOpt.get()));
    }

    public ResponseEntity<Void> updateCuisineType(@ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage, @ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId, @ApiParam(value = "CuisineType to update.") @Valid @RequestBody CuisineType body) {

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteCuisineType(@ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    private LanguageEnumBO checkAndExtractAcceptedLanguage(String acceptLanguage) throws ApiFieldsException {

        for (String language : acceptLanguage.split(ACCEPT_LANGUAGE_SEPARATOR)) {
            if (LanguageEnum.fromValue(language) != null){
                return LanguageEnumBO.fromValue(language);
            }
        }
        throw new ApiFieldsException(messages.get("error.badrequest.invalidparams.code"), messages.get("error.badrequest.invalidparams.desc"),
                Arrays.asList(new FieldError(messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.code"), HttpHeaders.ACCEPT_LANGUAGE, HttpHeaders.ACCEPT_LANGUAGE + " " + messages.get("error.badrequest.invalidparams.fields.headerparaminvalid.desc.enum") + " " + LanguageEnum.getValueList())));
    }

    private void checkAccept(String accept) throws ApiAcceptException {
        if (accept != null && !accept.equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new ApiAcceptException();
        }
    }

}
