package com.ojodev.cookinghero.recipes.api.controller;

import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.Measure;
import com.ojodev.cookinghero.recipes.api.model.MeasureNew;
import com.ojodev.cookinghero.recipes.api.model.MeasureUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.List;

@Controller
@Api(tags = "measures", value = "Measures used with ingredients")
public class MeasuresApiController implements MeasuresApi {

    private static final Logger log = LoggerFactory.getLogger(MeasuresApiController.class);

    public ResponseEntity<Void> addMeasure(@ApiParam(value = "Measure to add.") @Valid @RequestBody MeasureNew body) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteMeasure(@ApiParam(value = "Measure id.", required = true) @PathVariable("measure-id") String measureId) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Measure> getMeasure(@ApiParam(value = "Measure id.", required = true) @PathVariable("measure-id") String measureId,
                                              @ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage) {
        return new ResponseEntity<Measure>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Measure>> getMeasures(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage) {

        return new ResponseEntity<List<Measure>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateMeasure(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE, required = true) String acceptLanguage,
                                              @ApiParam(value = "Measure id.", required = true) @PathVariable("measure-id") String measureId,
                                              @ApiParam(value = "Measure to update.") @Valid @RequestBody MeasureUpdate body) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
