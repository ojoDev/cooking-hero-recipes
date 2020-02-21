package com.ojodev.cookinghero.recipes.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ojodev.cookinghero.recipes.api.model.Product;
import com.ojodev.cookinghero.recipes.api.model.ProductNew;
import com.ojodev.cookinghero.recipes.api.model.ProductUpdate;
import com.ojodev.cookinghero.recipes.api.model.ProductsSearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;

@Controller
@Api(tags = "products", value = "Products used in recipes")
public class ProductsApiController implements ProductsApi {

    private static final Logger log = LoggerFactory.getLogger(ProductsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    //TODO DMS: Ver si esto me vale para algo.
    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    //TODO DMS: Implementar params order
    public ResponseEntity<ProductsSearch> getProducts(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                                      @ApiParam(value = "Product name, singular or plural.", example = "potato") @Valid @RequestParam(value = "name", required = false) String name,
                                                      @Min(1) @Max(100) @ApiParam(value = "Maximum number of records returned, by default 10.", example = "10") @Valid @RequestParam(value = "limit", required = false) Integer limit,
                                                      @Min(0) @ApiParam(value = "Number of page for skip (pagination).", example = "0") @Valid @RequestParam(value = "offset", required = false) Integer offset) {
        //TODO DMS: Por hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> addProduct(@ApiParam(value = "Product info") @Valid @RequestBody ProductNew body) {
        //TODO DMS: Por hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Product> getProduct(@ApiParam(value = "Product id.", required = true, example = "potato") @PathVariable("product-id") String productId,
                                              @ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "es") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage) {
        //TODO DMS: Por hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Void> updateProduct(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true, example = "en") @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                              @ApiParam(value = "Product id.", required = true, example = "potato") @PathVariable("product-id") String productId,
                                              @ApiParam(value = "Product to update.") @Valid @RequestBody ProductUpdate body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product id.", required = true, example = "potato") @PathVariable("product-id") String productId) {
        //TODO DMS: Por hacer
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
