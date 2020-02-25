package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Product used in recipes into a **Ingredient**. An **Hero** can be freely define a new product as CREATED_BY_USER.   An **Admin** can create a new product as APPROVED_BY_ADMIN, or change (approve) a user product status. This products are show to all users to select in this recipes.
 */
@ApiModel(description = "Product used in recipes into a **Ingredient**. An **Hero** can be freely define a new product as CREATED_BY_USER.   An **Admin** can create a new product as APPROVED_BY_ADMIN, or change (approve) a user product status. This products are show to all users to select in this recipes. ")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @JsonProperty("id")
    @ApiModelProperty(example = "53", required = true, value = "Product id.", position = 0)
    @NotNull
    private String id;

    @JsonProperty("name")
    @ApiModelProperty(example = "{\"singular\":\"potato\",\"plural\":\"potatoes\"}", required = true, value = "Product name.", position = 1)
    @NotNull
    private DescriptiveName name;

    @ApiModelProperty(value = "Product status", position = 2)
    @Valid
    @JsonProperty("status")
    private ProductStatusEnum status;

}
