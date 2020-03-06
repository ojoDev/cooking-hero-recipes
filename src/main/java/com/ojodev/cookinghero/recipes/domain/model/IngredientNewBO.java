package com.ojodev.cookinghero.recipes.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Ingredient
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class IngredientNewBO extends IdentifiableBO {

    private String recipeId;

    private String productName;

    private BigDecimal quantity;

    private String measureId;


    public IngredientNewBO(String recipeId, String productName) {
        super(recipeId + "-" + productName);
        if (StringUtils.isEmpty(recipeId) || StringUtils.isEmpty(productName)) {
            throw new IllegalArgumentException("Needs to include at least a recipeId and a productName fields");
        }
        this.recipeId = recipeId;
        this.productName = productName;
    }

    public IngredientNewBO(String recipeId, String productName, BigDecimal quantity) {
        this(recipeId, productName);
        this.quantity = quantity;
    }

    public IngredientNewBO(String recipeId, String productName, BigDecimal quantity, String measureId) {
        this(recipeId, productName, quantity);
        this.quantity = quantity;
        this.measureId = measureId;

    }

}

