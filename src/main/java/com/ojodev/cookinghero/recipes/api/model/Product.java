package com.ojodev.cookinghero.recipes.api.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Product
 */
@ApiModel(description = "product of a ingredient")
@Validated
@Data
public class Product {

    @JsonProperty("name")
    @ApiModelProperty(example = "potato", required = true, value = "product name")
    @NotNull
    private String name;

}
