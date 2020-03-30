package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.config.Messages;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@Api(tags = "recipes", description = "Hero recipes")
public class StepsApiController implements StepsApi {

    @Autowired
    private Messages messages;


    @Override
    public ResponseEntity<List<Step>> getSteps(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> addStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                        @ApiParam(value = "Step to add.") @Valid @RequestBody Step body) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> updateStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                           @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber,
                                           @ApiParam(value = "Step description to update.") @Valid @RequestBody Step body) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> deleteStep(@ApiParam(value = "Recipe id.", required = true) @PathVariable("recipe-id") String recipeId,
                                           @Min(1) @ApiParam(value = "Step number in the step list of the recipe (first: 1).", required = true) @PathVariable("step-number") Integer stepNumber) {
        //TODO Hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
