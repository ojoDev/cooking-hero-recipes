package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeUpdate;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.config.RecipesConfig;
import com.ojodev.cookinghero.recipes.domain.constants.RecipeConstants;
import com.ojodev.cookinghero.recipes.domain.exception.*;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeMultiLanguageBO;
import com.ojodev.cookinghero.recipes.domain.model.LanguageEnumBO;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMapper;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypesMultipleLanguageMapper;
import com.ojodev.cookinghero.recipes.mapper.LanguageEnumMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@Api(tags = "cuisine-types", value = "Cuisine types of recipes")
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


    public ResponseEntity<List<CuisineType>> getCuisineTypes(
            @ApiParam(value = "User need to choose a language to receive data.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage,
            @ApiParam(value = "Cuisine type name. Partial searches allowed.", example = "veggie") @Valid @RequestParam(value = "name", required = false) String name) throws ApiException {

        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(cuisineTypeMapper.toCuisineTypeList(cuisineTypesBusiness.getCuisineTypes(name, language)));

    }

    public ResponseEntity<Void> addCuisineType(
            @ApiParam(value = "Cuisine type to add.") @Valid @RequestBody CuisineTypeNew body) throws ApiException {
        validateBody(body);
        String id = saveCuisineType(body);
        return ResponseEntity.created(generateLocationHeader(id)).build();
    }

    private URI generateLocationHeader(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
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


    /**
     * Save cuisine type
     *
     * @param cuisineTypeNew new cuisine type
     * @return id of new cuisine type
     * @throws ApiException exception
     */
    private String saveCuisineType(CuisineTypeNew cuisineTypeNew) throws ApiException {
        CuisineTypeMultiLanguageBO cuisineTypeMultiLanguageBO = cuisineTypesMultipleLanguageMapper.toCuisineTypeMultiLanguageBO(cuisineTypeNew, config.getDefaultLanguage());
        cuisineTypesBusiness.addCuisineType(cuisineTypeMultiLanguageBO);
        return cuisineTypeMultiLanguageBO.getId();
    }


    public ResponseEntity<CuisineType> getCuisineType(@ApiParam(value = "Cuisine type id.", required = true, example = "veggie") @PathVariable("cuisine-type-id") String cuisineTypeId,
                                                      @ApiParam(value = "User need to choose a language to receive data.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage) throws ApiException {

        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);

        Optional<CuisineTypeBO> cuisineTypeBOOpt = cuisineTypesBusiness.getCuisineType(cuisineTypeId, language);
        if (!cuisineTypeBOOpt.isPresent()) {
            throw new NotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                .body(cuisineTypeMapper.toCuisineType(cuisineTypeBOOpt.get()));
    }

    public ResponseEntity<Void> updateCuisineType(@ApiParam(value = "User need to choose a language to receive data.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage,
                                                  @ApiParam(value = "Cuisine type id.", required = true, example = "veggie") @PathVariable("cuisine-type-id") String cuisineTypeId,
                                                  @ApiParam(value = "CuisineType to update.") @Valid @RequestBody CuisineTypeUpdate body) throws ApiException {

        LanguageEnumBO language = checkAndExtractAcceptedLanguage(acceptLanguage);
        cuisineTypesBusiness.addOrReplaceCuisineType(new CuisineTypeBO(cuisineTypeId, body.getName(), language));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> deleteCuisineType(@ApiParam(value = "Cuisine type id.", required = true, example = "veggie") @PathVariable("cuisine-type-id") String cuisineTypeId) throws NotFoundException {
        cuisineTypesBusiness.deleteCuisineType(cuisineTypeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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


}
