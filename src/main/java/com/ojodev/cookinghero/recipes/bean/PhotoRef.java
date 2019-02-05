package com.ojodev.cookinghero.recipes.bean;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * PhotoRef
 */
@Validated
@Data
@AllArgsConstructor
public class PhotoRef {

	@JsonProperty("href")
	@ApiModelProperty(example = "/media/photo/21344123123", required = true, value = "photo href")
	@NotNull
	private String href;

}
