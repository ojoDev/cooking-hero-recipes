package com.ojodev.cookinghero.recipes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Media (image or video) associated to a recipe.
 */
@ApiModel(description = "Media (image or video) associated to a recipe.")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {


	@JsonProperty("mediaType")
	@ApiModelProperty(example = "IMAGE", required = true, value = "Media type (image or video).")
	@NotNull
	private MediaTypeEnum mediaType;

	@JsonProperty("href")
	@ApiModelProperty(example = "https://www.mycompany.com/api/media/v1/media/21344123123", required = true, value = "Href to image or video resource.")
	@NotNull
	private String href;

}
