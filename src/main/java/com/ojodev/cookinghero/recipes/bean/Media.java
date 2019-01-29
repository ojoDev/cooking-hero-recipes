package com.ojodev.cookinghero.recipes.bean;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * media (photo or video) associated to a recipe
 */
@ApiModel(description = "media (photo or video) associated to a recipe")
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.SpringCodegen", date = "2019-01-26T22:57:21.236+01:00[Europe/Paris]")

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

	private MediaTypeEnum mediaType = null;

	@JsonProperty("href")

	private String href = null;

	public Media mediaType(MediaTypeEnum mediaType) {
		this.mediaType = mediaType;
		return this;
	}

	/**
	 * 
	 * media type (photo or video)
	 * 
	 * 
	 * 
	 * 
	 * @return mediaType
	 **/

	@ApiModelProperty(example = "PHOTO", required = true, value = "media type (photo or video)")

	@NotNull

	public MediaTypeEnum getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaTypeEnum mediaType) {
		this.mediaType = mediaType;
	}

	public Media href(String href) {
		this.href = href;
		return this;
	}

	/**
	 * 
	 * href to photo or video resource
	 * 
	 * 
	 * 
	 * 
	 * @return href
	 **/

	@ApiModelProperty(example = "media/photos/23425324", required = true, value = "href to photo or video resource")

	@NotNull

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Media media = (Media) o;
		return Objects.equals(this.mediaType, media.mediaType) && Objects.equals(this.href, media.href);
	}

	@Override
	public int hashCode() {
		return Objects.hash(mediaType, href);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Media {\n");

		sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
		sb.append("    href: ").append(toIndentedString(href)).append("\n");
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
