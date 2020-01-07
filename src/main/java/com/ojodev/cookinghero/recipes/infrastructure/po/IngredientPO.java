package com.ojodev.cookinghero.recipes.infrastructure.po;

import lombok.Data;

@Data
public class IngredientPO {

	private String product;

	private Integer quantity;

	private String measure;

	
	public IngredientPO(String product) {
		super();
		this.product = product;
	}

	public IngredientPO(String product, Integer quantity, String measure) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.measure = measure;
	}

	public IngredientPO() {
		super();
	}

}
