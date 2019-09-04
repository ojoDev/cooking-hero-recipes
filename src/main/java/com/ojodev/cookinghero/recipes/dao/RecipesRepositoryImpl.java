package com.ojodev.cookinghero.recipes.dao;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.ojodev.cookinghero.recipes.enume.UpsertResultEnum;
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
	public List<RecipePO> findRecipes(String recipeName) {
		Query query = new Query();
		if (recipeName != null) {
			Pattern likeRecipeName = Pattern.compile(Pattern.quote(recipeName));
			query.addCriteria(Criteria.where("name").is(likeRecipeName));
		}
		return mongoTemplate.find(query, RecipePO.class);
	}

	public RecipePO findRecipeById(String id) {
		return mongoTemplate.findById(new ObjectId(id), RecipePO.class);
	}

	@Override
	public String addRecipe(RecipePO recipe) {
		RecipePO insertedRecipe = mongoTemplate.insert(recipe);
		return insertedRecipe == null ? null : insertedRecipe.getId().toString();
	}
	
	@Override
	public UpsertResultEnum upsertRecipe(RecipePO recipe) {
		Update updateData = generateFullUpdate(recipe);
		UpdateResult updateResult = mongoTemplate.upsert(query(where("id").is(recipe.getId())), updateData, RecipePO.class);
		return updateResult != null && updateResult.getMatchedCount() > 0 ? UpsertResultEnum.UPDATED : UpsertResultEnum.CREATED;
	}

	//TODO DMS Mejorarlo haciendo una clase genérica que modifique todos los atributos
	private Update generateFullUpdate(RecipePO recipe) {
		
		Update update = new Update();
				
		Field fields[] = RecipePO.class.getDeclaredFields();
		for (Field field: fields) {
			  String fieldName = field.getName();
			try { 
				field.setAccessible(true);
				Object value = field.get(recipe);field.getType();
				org.springframework.data.mongodb.core.mapping.Field mongoAnnotation = field.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
				  if ( mongoAnnotation != null) {
					  fieldName = mongoAnnotation.value();
				  }
				  update.set(fieldName, value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		//Update update = new Update().set("name", recipe.getName().).set("description", recipe.getDescription());
		return update;
	}

	@Override
	public RecipePO deleteRecipe(String recipeId) {
		return mongoTemplate.findAndRemove(new Query(Criteria.where("_id").is(recipeId)), RecipePO.class);
	}

}
