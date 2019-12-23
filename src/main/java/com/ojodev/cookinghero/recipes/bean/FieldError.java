package com.ojodev.cookinghero.recipes.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * API field error
 */
@ApiModel(description = "API field error")
@Validated
@Data
public class FieldError {

    @JsonProperty("code")
    @ApiModelProperty(example = "NAME_NOT_VALID", value = "field error code")
    private String code;

    @JsonProperty("field")
    @ApiModelProperty(example = "username", value = "field name")
    private String field;

    @JsonProperty("description")
    @ApiModelProperty(example = "username is not valid. Minimun length is 3.", value = "error description associated with the field")
    private String description;

}