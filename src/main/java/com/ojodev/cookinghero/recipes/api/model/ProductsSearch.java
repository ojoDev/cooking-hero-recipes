package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * Product list get response.
 */
@ApiModel(description = "Product list get response.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSearch {

    @JsonProperty("content")
    @ApiModelProperty(value = "Products returned list.", position = 0)
    @Valid
    private List<Product> content;

    @JsonProperty("pagination")
    @ApiModelProperty(value = "Pagination info", position = 1)
    private PaginationInfo pagination;

}
