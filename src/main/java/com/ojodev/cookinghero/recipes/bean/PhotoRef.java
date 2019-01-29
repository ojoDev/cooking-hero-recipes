package com.ojodev.cookinghero.recipes.bean;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * PhotoRef
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.SpringCodegen", date = "2019-01-26T17:12:37.629+01:00[Europe/Paris]")

public class PhotoRef {

	public PhotoRef() {
		super();
	}

	public PhotoRef(String href) {
		super();
		this.href = href;
	}

	@JsonProperty("href")

	private String href = null;

	public PhotoRef href(String href) {
		this.href = href;
		return this;
	}

	/**
	 * 
	 * photo href
	 * 
	 * 
	 * 
	 * 
	 * @return href
	 **/

	@ApiModelProperty(example = "/media/photo/21344123123", required = true, value = "photo href")

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
		PhotoRef photoRef = (PhotoRef) o;
		return Objects.equals(this.href, photoRef.href);
	}

	@Override
	public int hashCode() {
		return Objects.hash(href);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PhotoRef {\n");

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
