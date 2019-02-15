package com.ojodev.cookinghero.recipes.bean;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * media (photo or video) associated to a recipe
 */
@ApiModel(description = "media (photo or video) associated to a recipe")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {

	/**
	 * media type (photo or video)
	 */
	public enum MediaTypeEnum {

		PHOTO("PHOTO"),

		VIDEO("VIDEO");

		private String value;

		MediaTypeEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static MediaTypeEnum fromValue(String text) {
			for (MediaTypeEnum b : MediaTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("mediaType")
	@ApiModelProperty(example = "PHOTO", required = true, value = "media type (photo or video)")
	@NotNull
	private MediaTypeEnum mediaType;

	@JsonProperty("href")
	@ApiModelProperty(example = "media/photos/23425324", required = true, value = "href to photo or video resource")
	@NotNull
	private String href;

}
