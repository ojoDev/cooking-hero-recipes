package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Cuisine type new.
 */
@ApiModel(description = "Cuisine type.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuisineTypeNew {

    @JsonProperty("names")
    @ApiModelProperty(required = true, value = "List of cuisine names and his languages.")
    @NotNull
    private List<CuisineTypeNewName> names;

}
