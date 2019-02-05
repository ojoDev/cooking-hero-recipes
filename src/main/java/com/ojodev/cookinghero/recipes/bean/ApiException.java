package com.ojodev.cookinghero.recipes.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiException extends Exception{
	
    private String code;
    private String description;
 
}
