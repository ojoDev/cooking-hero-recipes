package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * Product fields to update.
 */
@ApiModel(description = "Product fields to update.")
@Validated
public class ProductUpdate {

    @JsonProperty("name")
    @ApiModelProperty(example = "{\"singular\":\"potato\",\"plural\":\"potatoes\"}", value = "Measure description.", position = 0)
    private Optional<DescriptiveNameUpdate> name = Optional.empty();

    @JsonProperty("status")
    @ApiModelProperty(value = "Product status", position = 1)
    private Optional<ProductStatusEnum> status = Optional.empty();

    public ProductUpdate() {
    }

    public ProductUpdate(DescriptiveNameUpdate name, ProductStatusEnum status) {
        this.name = Optional.ofNullable(name);
        this.status = Optional.ofNullable(status);
    }


    /**
     * name
     **/

    public DescriptiveNameUpdate getName() {
        return name.isPresent() ? name.get() : null;
    }

    @ApiModelProperty(hidden = true)
    public Optional<DescriptiveNameUpdate> getNameOpt() {
        return name;
    }

    public void setName(DescriptiveNameUpdate name) {
        this.name = name == null ? null : Optional.ofNullable(name);
    }

    /**
     * status
     **/

    public ProductStatusEnum getStatus() {
        return status.isPresent() ? status.get() : null;
    }

    @ApiModelProperty(hidden = true)
    public Optional<ProductStatusEnum> getStatusOpt() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status == null ? null : Optional.ofNullable(status);
    }
}
