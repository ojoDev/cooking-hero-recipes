package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Pagination info.
 */
@ApiModel(description = "Pagination info.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationLinks {

    @JsonProperty("first")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?limit=10", value = "First page.")
    private String first;

    @JsonProperty("prev")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=10&limit=10", value = "Previous page.")
    private String prev;

    @JsonProperty("self")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=20&limit=10", value = "Actual searched page.")
    private String self;

    @JsonProperty("next")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=30&limit=10", value = "Next page.")
    private String next;

    @JsonProperty("last")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=60&limit=10", value = "Last page.")
    private String last;

}
