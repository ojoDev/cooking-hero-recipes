package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.net.URL;

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
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?limit=10", value = "First page.", position = 0)
    private String first;

    @JsonProperty("prev")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=10&limit=10", value = "Previous page.", position = 1)
    private String prev;

    @JsonProperty("self")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=20&limit=10", value = "Actual searched page.", position = 2)
    private String self;

    @JsonProperty("next")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=30&limit=10", value = "Next page.", position = 3)
    private String next;

    @JsonProperty("last")
    @ApiModelProperty(example = "http://www.mycompany.com/api/users/v1/users?offset=60&limit=10", value = "Last page.", position = 4)
    private String last;

    public PaginationLinks(Integer offset, Integer limit, Integer totalElements, URL url) {
        Integer page =  offset / limit + 1;
        Integer totalPages = Integer.valueOf((int) Math.ceil(totalElements.doubleValue() /  limit.doubleValue()));
        if (page > 1) {
            this.first = buildUrl(0, limit, url);
          //  this.prev = buildUrl(((page - 2) * limit)  < limit ? 0 : ((page - 2) * limit), limit, url);
            this.prev = buildUrl(offset - limit  < limit ? 0 : offset - limit, limit, url);
        }
        this.self = buildUrl(offset, limit, url);
        if (page < totalPages) {
          //  this.next = buildUrl((page) * limit , limit, url);
            this.next = buildUrl(offset + limit > totalElements ? offset : offset + limit, limit, url);
          //  this.last = buildUrl((totalPages - 1) * limit, limit, url);
            this.last = buildUrl((totalPages - 1) * limit, limit, url);

        }
    }

    private String buildUrl(Integer offset, Integer limit, URL url) {
        String sUrl = "";
        if (url == null) {
            throw new IllegalArgumentException("url is mandatory");
        }
        try {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance().uri(url.toURI());
            if (offset != null && offset > 0)  {
                uriComponentsBuilder.queryParam("offset", offset);
            }
            if (limit != null)  {
                uriComponentsBuilder.queryParam("limit", limit);
            }
            sUrl = uriComponentsBuilder.build().toUriString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("URL is not valid");
        }
        return sUrl;
    }

}
