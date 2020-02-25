package com.ojodev.cookinghero.recipes.domain.exception;

import lombok.*;

import java.util.Objects;


@SuppressWarnings("serial")
public class ApiException extends Exception {

	private final String code;
	private final String description;

	public ApiException(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ApiException{");
		sb.append("code='").append(code).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ApiException that = (ApiException) o;
		return Objects.equals(code, that.code) &&
				Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, description);
	}
}
