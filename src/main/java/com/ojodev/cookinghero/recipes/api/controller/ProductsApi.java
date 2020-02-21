package com.ojodev.cookinghero.recipes.api.controller;


import com.google.common.net.HttpHeaders;
import com.ojodev.cookinghero.recipes.api.model.*;
import com.ojodev.cookinghero.recipes.domain.exception.ApiException;
import com.ojodev.cookinghero.recipes.domain.exception.ApiFieldsException;
import com.ojodev.cookinghero.recipes.domain.exception.NotFoundException;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Api(value = "products", description= "Products used in recipes")
public interface ProductsApi {

    @ApiOperation(value = "Get product list", nickname = "getProducts", notes = "Search products in a specific language. ", response = ProductsSearch.class, tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product list.", response = ProductsSearch.class),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/products",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<ProductsSearch> getProducts(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                               @ApiParam(value = "Product name, singular or plural.") @Valid @RequestParam(value = "name", required = false) String name,
                                               @Min(1) @Max(100) @ApiParam(value = "Maximum number of records returned, by default 10.") @Valid @RequestParam(value = "limit", required = false) Integer limit,
                                               @Min(0) @ApiParam(value = "Number of page for skip (pagination).") @Valid @RequestParam(value = "offset", required = false) Integer offset);


    @ApiOperation(value = "Add a product", nickname = "addProduct", notes = "Add a new product.\nYou can add multiple languages in a single request. English (en) is mandatory.\nAn **Hero** can be freely define a new product as CREATED_BY_USER.\nAn **Admin** can create a new product as APPROVED_BY_ADMIN, or change (approve) a user product status. This products are show to all users to select in this recipes. ", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @PostMapping(value = "/products",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> addProduct(@ApiParam(value = "Product info") @Valid @RequestBody ProductNew body) throws ApiException;


    @ApiOperation(value = "Get a product", nickname = "getProduct", notes = "Search for a product in a specific language. ", response = Product.class, tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product.", response = Product.class),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @GetMapping(value = "/products/{product-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Product> getProduct(@ApiParam(value = "Product id.", required = true) @PathVariable("product-id") String productId,
                                       @ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage) throws ApiException;


    @ApiOperation(value = "Update a product", nickname = "updateProduct", notes = "Update a product.    You can add more languages to a exist product with Accept-Language header.   Only an **Admin** can change the status. ", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product description updated."),
            @ApiResponse(code = 400, message = "Bad input parameter.", response = ApiFieldsError.class),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @RequestMapping(value = "/products/{product-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PATCH)
    ResponseEntity<Void> updateProduct(@ApiParam(value = "User need to choose a language to receive data. Valid values are: en, es.", required = true) @RequestHeader(value = HttpHeaders.ACCEPT_LANGUAGE) String acceptLanguage,
                                       @ApiParam(value = "Product id.", required = true) @PathVariable("product-id") String productId,
                                       @ApiParam(value = "Product to update.") @Valid @RequestBody ProductUpdate body) throws ApiException;

    @ApiOperation(value = "Delete a product", nickname = "deleteProduct", notes = "Delete a product. ", tags = {"products"})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product deleted."),
            @ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource.", response = ApiError.class),
            @ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it.", response = ApiError.class),
            @ApiResponse(code = 404, message = "Not found.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class)})
    @DeleteMapping(value = "/products/{product-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product id.", required = true) @PathVariable("product-id") String productId) throws NotFoundException;

}
