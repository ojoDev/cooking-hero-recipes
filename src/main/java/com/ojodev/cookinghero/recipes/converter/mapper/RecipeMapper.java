package com.ojodev.cookinghero.recipes.converter.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.po.RecipePO;

@Mapper(componentModel = "spring", uses=Formatter.class)
public interface RecipeMapper {

	@Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
	@Mapping(source = "photoId", target = "photo", qualifiedByName = "toObjectId")
	Recipe toRecipe(RecipePO recipePO);

	@Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
	RecipePO toRecipePO(Recipe recipe);
	
}
