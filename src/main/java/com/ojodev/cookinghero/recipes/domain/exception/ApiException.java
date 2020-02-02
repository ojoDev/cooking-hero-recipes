package com.ojodev.cookinghero.recipes.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
public class ApiException extends Exception {

	private String code;
	private String description;


}
