package com.ojodev.cookinghero.recipes.converter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.bean.RecipeRequest;
import com.ojodev.cookinghero.recipes.po.RecipePO;

@Mapper(componentModel = "spring", uses=Formatter.class)
public interface RecipeMapper {

	@Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
	@Mapping(source = "photoId", target = "photo", qualifiedByName = "toPhotoRef")
	Recipe toRecipe(RecipePO recipePO);

	@Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
	RecipePO toRecipePO(Recipe recipe);

	@Mapping(source = "photo", target = "photoId", qualifiedByName = "toPhotoId")
	RecipePO toRecipePO(RecipeRequest recipe);
	
}
