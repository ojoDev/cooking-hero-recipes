package com.ojodev.cookinghero.recipes.bean;

public class ApiException extends Exception {

	protected String code;
	protected String description;

	public ApiException() {
		super();
	}

	public ApiException(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
