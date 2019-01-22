package com.ojodev.cookinghero.recipes.bean;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ApiError
 */
@Validated
public class ApiError {
	
	@JsonProperty("code")
	private String code = null;

	@JsonProperty("description")
	private String description = null;
	

	public ApiError() {
		super();
	}

	public ApiError(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public ApiError code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * error code id
	 * 
	 * @return code
	 **/
	@ApiModelProperty(example = "NAME_NOT_VALID", value = "error code id")

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ApiError description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * error description
	 * 
	 * @return description
	 **/
	@ApiModelProperty(example = "Ingredient name is not valid", value = "error description")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiError apiError = (ApiError) o;
		return Objects.equals(this.code, apiError.code) && Objects.equals(this.description, apiError.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, description);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ApiError {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
