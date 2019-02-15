package com.ojodev.cookinghero.recipes.po;

import com.ojodev.cookinghero.recipes.enume.MediaType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaPO {
	
	private MediaType type;
	
	private String id;

}
