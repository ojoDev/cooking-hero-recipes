package com.ojodev.cookinghero.recipes.bean;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * step to cook a recipe
 */
@ApiModel(description = "step to cook a recipe")
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.SpringCodegen", date = "2019-01-26T22:57:21.236+01:00[Europe/Paris]")

public class Step {

	@JsonProperty("description")

	private String description = null;

	@JsonProperty("time")

	private BigDecimal time = null;

	@JsonProperty("media")

	private Media media = null;

	public Step description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * 
	 * description of ordered actions to cook the recipe
	 * 
	 * 
	 * 
	 * 
	 * @return description
	 **/

	@ApiModelProperty(example = "In a large frying pan or skillet, heat olive oil over medium-high heat.", required = true, value = "description of ordered actions to cook the recipe")

	@NotNull

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Step time(BigDecimal time) {
		this.time = time;
		return this;
	}

	/**
	 * 
	 * time in minutes to finish this step (cooking or/and preparation time)
	 * 
	 * 
	 * 
	 * 
	 * @return time
	 **/

	@ApiModelProperty(example = "15", value = "time in minutes to finish this step (cooking or/and preparation time)")

	@Valid

	public BigDecimal getTime() {
		return time;
	}

	public void setTime(BigDecimal time) {
		this.time = time;
	}

	public Step media(Media media) {
		this.media = media;
		return this;
	}

	/**
	 * 
	 * 
	 * Get media
	 * 
	 * 
	 * 
	 * @return media
	 **/

	@ApiModelProperty(value = "")

	@Valid

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Step step = (Step) o;
		return Objects.equals(this.description, step.description) && Objects.equals(this.time, step.time)
				&& Objects.equals(this.media, step.media);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, time, media);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Step {\n");

		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    time: ").append(toIndentedString(time)).append("\n");
		sb.append("    media: ").append(toIndentedString(media)).append("\n");
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
