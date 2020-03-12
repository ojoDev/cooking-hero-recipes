package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(description = "New ingredient with a existent measure and a possible new or existent product.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientNew {

    @JsonProperty("productName")
    @ApiModelProperty(example = "potato", required = true, value = "Product name. If product not exist, create a new product (status: CREATED_BY_USER) ", position = 0)
    @NotNull
    private String productName;

    @JsonProperty("quantity")
    @ApiModelProperty(example = "2", value = "Number of product units or quantity.", position = 1)
    @Valid
    @DecimalMin("0.01")
    private BigDecimal quantity;

    @JsonProperty("measureId")
    @ApiModelProperty(example = "gr", value = "Measure id, related to a existent measure.", position = 2)
    private String measureId;

}