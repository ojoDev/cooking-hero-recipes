package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@ApiModel(description = "Update ingredient with a existent measure and a possible new or existent product.")
@Validated
public class IngredientUpdate {

    @JsonProperty("productName")
    @ApiModelProperty(example = "potato", required = true, value = "Product name. If product not exist, create a new product (status: CREATED_BY_USER) ", position = 0)
    @NotNull
    private Optional<String> productName = Optional.empty();

    @JsonProperty("quantity")
    private Optional<BigDecimal> quantity = Optional.empty();

    @JsonProperty("measure")
    @ApiModelProperty(value = "Measure reference with id, related to a existent measure.", position = 2)
    private Optional<MeasureRef> measure = Optional.empty();

    /**
     * productName
     */
    public String getProductName() {
        return productName != null && productName.isPresent() ? productName.get() : null;
    }

    @ApiModelProperty(hidden = true)
    public Optional<String> getProductNameOpt() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : Optional.ofNullable(productName);
    }

    /**
     * quantity
     */
    @ApiModelProperty(example = "2", value = "Number of product units or quantity.", position = 1)
    @Valid
    @DecimalMin("0.01")
    public BigDecimal getQuantity() {
        return quantity != null && quantity.isPresent() ? quantity.get() : null;
    }

    @ApiModelProperty(hidden = true)
    public Optional<BigDecimal> getQuantityOpt() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity == null ? null : Optional.ofNullable(quantity);
    }

    /**
     * measure
     */
    public MeasureRef getMeasure() {
        return measure != null && measure.isPresent() ? measure.get() : null;
    }

    @ApiModelProperty(hidden = true)
    public Optional<MeasureRef> getMeasureOpt() {
        return measure;
    }

    public void setMeasure(MeasureRef measure) {
        this.measure = measure == null ? null : Optional.ofNullable(measure);
    }

}