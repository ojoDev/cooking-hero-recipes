package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Ingredient
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.SpringCodegen", date = "2019-01-23T17:27:32.003+01:00[Europe/Paris]")

public class Ingredient {

	@JsonProperty("product")

	private String product = null;

	@JsonProperty("quantity")

	private BigDecimal quantity = null;

	@JsonProperty("unit")

	private String unit = null;

	public Ingredient product(String product) {
		this.product = product;
		return this;
	}

	/**
	 * 
	 * product name
	 * 
	 * 
	 * 
	 * 
	 * @return product
	 **/

	@ApiModelProperty(example = "potatoes", required = true, value = "product name")

	@NotNull

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Ingredient quantity(BigDecimal quantity) {
		this.quantity = quantity;
		return this;
	}

	/**
	 * 
	 * number of product units or quantity
	 * 
	 * 
	 * 
	 * 
	 * @return quantity
	 **/

	@ApiModelProperty(example = "2", value = "number of product units or quantity")

	@Valid

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Ingredient unit(String unit) {
		this.unit = unit;
		return this;
	}

	/**
	 * 
	 * type of measure
	 * 
	 * 
	 * 
	 * 
	 * @return unit
	 **/

	@ApiModelProperty(example = "gr", value = "type of measure")

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Ingredient ingredient = (Ingredient) o;
		return Objects.equals(this.product, ingredient.product) && Objects.equals(this.quantity, ingredient.quantity)
				&& Objects.equals(this.unit, ingredient.unit);
	}

	@Override
	public int hashCode() {
		return Objects.hash(product, quantity, unit);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Ingredient {\n");

		sb.append("    product: ").append(toIndentedString(product)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
