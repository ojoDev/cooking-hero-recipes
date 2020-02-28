package com.ojodev.cookinghero.recipes.domain.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * Ingredient
 */
@ApiModel(description = "Ingredient and its quantity used in a recipe.")
@Validated
@Data
public class IngredientBO {

    private String id;

    private ProductBO product;

    private BigDecimal quantity;

    private MeasureBO measure;

    public IngredientBO(ProductBO product) {
        this.product = product;
    }

    public IngredientBO(ProductBO product, BigDecimal quantity, MeasureBO measure) {
        this.product = product;
        this.quantity = quantity;
        this.measure = measure;
    }
}

