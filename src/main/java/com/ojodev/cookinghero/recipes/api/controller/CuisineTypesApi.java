package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(value = "cuisine-types")
public interface CuisineTypesApi {

    @ApiOperation(value = "Get cuisine types", nickname = "getCuisineTypes", notes = "Search cuisine types in a specific language. ", response = CuisineType.class, responseContainer = "List", tags={ "cuisine-types"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cuisine type list.", response = CuisineType.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @GetMapping(value = "/cuisine-types",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<List<CuisineType>> getCuisineTypes(@ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                                      @ApiParam(value = "Cuisine type name. Partial searches allowed.") @Valid @RequestParam(value = "name", required = false) String name) throws ApiException;


    @ApiOperation(value = "Add a cuisine type", nickname = "addCuisineType", notes = "Add a new cuisine type.   You can add multiple languages in a single request. English (en) is mandatory. ", tags={ "cuisine-types"})
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Resource created."),
        @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
        @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
        @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
        @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @PostMapping(value = "/cuisine-types",
        produces = { MediaType.APPLICATION_JSON_VALUE },
        consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<Void> addCuisineType(@ApiParam(value = "Cuisine type to add.") @Valid @RequestBody CuisineTypeNew body) throws ApiException;

    @ApiOperation(value = "Get a cuisine type by id", nickname = "getCuisineType", notes = "Search for a cuisine type in a specific language. ", response = CuisineType.class, tags={ "cuisine-types"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cuisine Type.", response = CuisineType.class),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @GetMapping(value = "/cuisine-types/{cuisine-type-id}",
            produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<CuisineType> getCuisineType(@ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId,
                                               @ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage) throws ApiException;

    @ApiOperation(value = "Update a cuisine type", nickname = "updateCuisineType", notes = "Update a cuisineType.    You can add more languages to a exist cuisine type with Accept-Language header.", tags={ "cuisine-types"})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cuisine type description updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @PatchMapping(value = "/cuisine-types/{cuisine-type-id}",
            produces = { MediaType.APPLICATION_JSON_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<Void> updateCuisineType(@ApiParam(value = "User need to choose a language to receive data.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                           @ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId,
                                           @ApiParam(value = "CuisineType to update.") @Valid @RequestBody CuisineTypeUpdate body) throws ApiException;



    @ApiOperation(value = "Delete a cuisine type", nickname = "deleteCuisineType", notes = "Delete a cuisine type. ", tags={ "cuisine-types"})
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Cuisine type deleted."),
        @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
        @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
        @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
        @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @DeleteMapping(value = "/cuisine-types/{cuisine-type-id}",
        produces = { MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<Void> deleteCuisineType(@ApiParam(value = "Cuisine type id.", required = true) @PathVariable("cuisine-type-id") String cuisineTypeId) throws NotFoundException;


}
