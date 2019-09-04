package com.ojodev.cookinghero.recipes.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class ApiException extends Exception {

	protected String code;
	protected String description;


}
