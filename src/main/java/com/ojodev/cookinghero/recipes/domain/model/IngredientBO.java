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


    public IngredientBO() {
    }

    public IngredientBO(String id, ProductBO product) {
        this.id = id;
        this.product = product;
    }

    public IngredientBO(String id, ProductBO product, BigDecimal quantity, MeasureBO measure) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.measure = measure;
    }
}

