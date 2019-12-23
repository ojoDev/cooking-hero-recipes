package com.ojodev.cookinghero.recipes.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * API  error with fields
 */
@ApiModel(description = "API  error with fields")
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class ApiFieldsError extends ApiError {

    @JsonProperty("fields")
    @ApiModelProperty(value = "multiple error field")
    private List<FieldError> fields;

}
