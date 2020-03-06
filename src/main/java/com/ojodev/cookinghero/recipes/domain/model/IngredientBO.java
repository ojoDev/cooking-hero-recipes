package com.ojodev.cookinghero.recipes.domain.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Ingredient
 */
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

