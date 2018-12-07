package com.ojodev.cookinghero.recipes.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

	@Override
	public void addRecipe(RecipePO recipe) {
		mongoTemplate.insert(recipe);
		
	}

}
