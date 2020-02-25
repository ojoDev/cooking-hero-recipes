package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.net.URL;

/**
 * Pagination info.
 */
@ApiModel(description = "Pagination info.")
@Validated
@Data
@NoArgsConstructor
public class PaginationInfo {

    @JsonProperty("offset")
    @ApiModelProperty(example = "20", required = true, value = "Pagination offset applied, number of objects skipped.", position = 0)
    @NotNull
    private Integer offset;

    @JsonProperty("limit")
    @ApiModelProperty(example = "10", required = true, value = "Pagination limit applied, number of object returned.", position = 1)
    @NotNull
    private Integer limit;

    @JsonProperty("page")
    @ApiModelProperty(example = "3", value = "Page number.", position = 2)
    private Integer page;

    @JsonProperty("totalPages")
    @ApiModelProperty(example = "7", value = "Total number of pages.", position = 3)
    private Integer totalPages;

    @JsonProperty("totalElements")
    @ApiModelProperty(example = "65", value = "Total number of objects without limit and offset.", position = 4)
    private Integer totalElements;

    @JsonProperty("links")
    @ApiModelProperty(value = "Pagination links", position = 5)
    private PaginationLinks links;

    public PaginationInfo(Integer offset, Integer limit, Integer totalElements, URL url) {
        this.offset = offset;
        this.limit = limit;
        this.page =  offset / limit + 1;
        this.totalPages = Integer.valueOf((int) Math.ceil(totalElements.doubleValue() /  limit.doubleValue()));
        this.totalElements = totalElements;
        this.links = new PaginationLinks(offset, limit, totalElements, url);
    }

}
