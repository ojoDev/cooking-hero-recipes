package com.ojodev.cookinghero.recipes.domain.model;

import com.ojodev.cookinghero.recipes.domain.enume.MediaTypeEnumBO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Media (image or video) associated to a recipe.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaRefBO {

	private MediaTypeEnumBO mediaType;

	private String id;

}
