package com.ojodev.cookinghero.recipes.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepPO {

	private String description;

	private Integer time;

	private MediaPO media;

}
