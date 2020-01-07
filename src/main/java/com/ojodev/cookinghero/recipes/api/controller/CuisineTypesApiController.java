package com.ojodev.cookinghero.recipes.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.CuisineType;
import com.ojodev.cookinghero.recipes.api.model.CuisineTypeNew;
import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.business.CuisineTypesBusiness;
import com.ojodev.cookinghero.recipes.mapper.CuisineTypeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@Api(value = "cuisine-types", description = "Cuisine types API")
public class CuisineTypesApiController implements CuisineTypesApi {

    @Autowired
    private CuisineTypesBusiness cuisineTypesBusiness;

    @Autowired
    private CuisineTypeMapper cuisineTypeMapper;

    private static final Logger log = LoggerFactory.getLogger(CuisineTypesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CuisineTypesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<CuisineType>> getCuisineTypes(
            @ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage,
            @ApiParam(value = "Cuisine type name. Partial searches allowed.") @Valid @RequestParam(value = "name", required = false) String name) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        if (accept != null && accept.contains(MediaType.APPLICATION_JSON_VALUE )) {
            return  ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_LANGUAGE, acceptLanguage)
                    .body(cuisineTypeMapper.toCuisineType(cuisineTypesBusiness.getCuisineTypes(Optional.ofNullable(name), Optional.of(LanguageEnum.fromValue(acceptLanguage)))));
        }
        return new ResponseEntity<List<CuisineType>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addCuisineType(@ApiParam(value = "Cuisine type to add.") @Valid @RequestBody CuisineTypeNew body) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CuisineType> getCuisineType(@ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId, @ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) LanguageEnum acceptLanguage) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        if (accept != null && accept.contains("application/json")) {
            try {


                return new ResponseEntity<CuisineType>(objectMapper.readValue("{\n  \"name\" : \"veggie\",\n  \"id\" : 15\n}", CuisineType.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CuisineType>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CuisineType>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateCuisineType(@ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) LanguageEnum acceptLanguage, @ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId, @ApiParam(value = "CuisineType to update.") @Valid @RequestBody CuisineType body) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteCuisineType(@ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId) {
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
