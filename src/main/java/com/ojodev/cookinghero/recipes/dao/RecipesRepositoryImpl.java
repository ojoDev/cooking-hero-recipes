package com.ojodev.cookinghero.recipes.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.po.RecipePO;

@Component
public class RecipesRepositoryImpl implements RecipesRepository {

    @Autowired
    MongoTemplate mongoTemplate;
    
	@Override
	public List<RecipePO> findRecipes() {
		return mongoTemplate.findAll(RecipePO.class);
	}
	
	public RecipePO findRecipe(String id) {
		return mongoTemplate.findById(new ObjectId(id), RecipePO.class);
	}

	@Override
	public String addRecipe(RecipePO recipe) {
		RecipePO insertedRecipe = mongoTemplate.insert(recipe);
		return insertedRecipe ==null ? null : insertedRecipe.getId().toString();
	}

	@Override
	public RecipePO deleteRecipe(String recipeId) {
		return mongoTemplate.findAndRemove(new Query(Criteria.where("_id").is(recipeId)), RecipePO.class);
	}

}
