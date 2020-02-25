package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * API field error
 */
@ApiModel(description = "API field error")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiFieldError {

    @JsonProperty("code")
    @ApiModelProperty(example = "NAME_NOT_VALID", value = "Field error code", position = 0)
    private String code;

    @JsonProperty("field")
    @ApiModelProperty(example = "username", value = "Field name", position = 1)
    private String field;

    @JsonProperty("description")
    @ApiModelProperty(example = "username is not valid. Minimum length is 3.", value = "Error description associated with the field", position = 2)
    private String description;

}