package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Pagination info.
 */
@ApiModel(description = "Pagination info.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationInfo {

    @JsonProperty("offset")
    @ApiModelProperty(example = "20", required = true, value = "Pagination offset applied, number of objects skipped.")
    @NotNull
    private BigDecimal offset;

    @JsonProperty("limit")
    @ApiModelProperty(example = "10", required = true, value = "Pagination limit applied, number of object returned.")
    @NotNull
    private BigDecimal limit;

    @JsonProperty("page")
    @ApiModelProperty(example = "3", value = "Page number.")
    private BigDecimal page;

    @JsonProperty("totalPages")
    @ApiModelProperty(example = "7", value = "Total number of pages.")
    private BigDecimal totalPages;

    @JsonProperty("totalElements")
    @ApiModelProperty(example = "65", value = "Total number of objects without limit and offset.")
    private BigDecimal totalElements;

    @JsonProperty("links")
    @ApiModelProperty(value = "Pagination links")
    private PaginationLinks links;

}
