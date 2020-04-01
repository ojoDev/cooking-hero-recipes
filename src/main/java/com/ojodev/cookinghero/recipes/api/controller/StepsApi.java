package com.ojodev.cookinghero.recipes.api.controller;


import com.ojodev.cookinghero.recipes.api.model.ApiError;
import com.ojodev.cookinghero.recipes.api.model.ApiFieldsError;
import com.ojodev.cookinghero.recipes.api.model.Step;
import com.ojodev.cookinghero.recipes.api.model.Tag;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Api(value = "recipes")
public interface StepsApi {



    @ApiOperation(value = "Get steps to cook the recipe", nickname = "getSteps", notes = "Search steps for a recipe. ", response = Step.class, responseContainer = "List", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Steps for cook a recipe.", response = Step.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/recipes/{recipe-id}/steps",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Step>> getSteps(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId);


    @ApiOperation(value = "Adds a step to a recipe step list", nickname = "addStep", notes = "Adds a new step (in order) to the list of steps in a recipe.", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PostMapping(value = "/recipes/{recipe-id}/steps",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                 @ApiParam(value = "Step to add.") @Valid @RequestBody Step body);


    @ApiOperation(value = "Update step in a recipe.", nickname = "updateStep", notes = "Update step info. ", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Step updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PutMapping(value = "/recipes/{recipe-id}/steps/{step-number}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> updateStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                    @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber,
                                    @ApiParam(value = "Step description to update.") @Valid @RequestBody Step body);


    @ApiOperation(value = "Delete step of a recipe", nickname = "deleteStep", notes = "Delete step of a recipe and reorder other steps in the recipe. ", tags = {Tag.RECIPES})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Step deleted and other steps ordered."),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @DeleteMapping(value = "/recipes/{recipe-id}/steps/{step-number}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> deleteStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                    @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber);


}

