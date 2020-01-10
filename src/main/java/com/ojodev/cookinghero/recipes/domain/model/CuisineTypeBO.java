package com.ojodev.cookinghero.recipes.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Cuisine type.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuisineTypeBO {

    private String id;

    private String name;

}
