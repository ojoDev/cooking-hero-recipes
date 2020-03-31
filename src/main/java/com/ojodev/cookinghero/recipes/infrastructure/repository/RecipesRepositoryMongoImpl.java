package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.mongodb.client.result.UpdateResult;
import com.ojodev.cookinghero.recipes.domain.enume.UpsertResultEnumBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipeMongoPO;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
@Slf4j
public class RecipesRepositoryMongoImpl implements RecipesRepositoryMongo {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<RecipeMongoPO> findRecipes() {
		return mongoTemplate.findAll(RecipeMongoPO.class);
	}

	@Override
	public List<RecipeMongoPO> findRecipes(String recipeName) {
		Query query = new Query();
		if (recipeName != null) {
			Pattern likeRecipeName = Pattern.compile(Pattern.quote(recipeName));
			query.addCriteria(Criteria.where("name").is(likeRecipeName));
		}
		return mongoTemplate.find(query, RecipeMongoPO.class);
	}

	public RecipeMongoPO findRecipeById(String id) {
		return mongoTemplate.findById(new ObjectId(id), RecipeMongoPO.class);
	}

	@Override
	public String addRecipe(RecipeMongoPO recipe) {
		RecipeMongoPO insertedRecipe = mongoTemplate.insert(recipe);
		return insertedRecipe == null ? null : insertedRecipe.getId().toString();
	}
	
	@Override
	public UpsertResultEnumBO upsertRecipe(RecipeMongoPO recipe) {
		Update updateData = generateFullUpdate(recipe);
		UpdateResult updateResult = mongoTemplate.upsert(query(where("id").is(recipe.getId())), updateData, RecipeMongoPO.class);
		return updateResult != null && updateResult.getMatchedCount() > 0 ? UpsertResultEnumBO.UPDATED : UpsertResultEnumBO.CREATED;
	}

	//TODO DMS Mejorarlo haciendo una clase genérica que modifique todos los atributos
	private Update generateFullUpdate(RecipeMongoPO recipe) {
		
		Update update = new Update();
				
		Field[] fields = RecipeMongoPO.class.getDeclaredFields();
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
				// TODO DMS Gestionar excepcion

			} catch (IllegalAccessException e) {
				// TODO DMS Gestionar excepcion
			}
			
		}

		//Update update = new Update().set("name", recipe.getName().).set("description", recipe.getDescription());
		return update;
	}

	@Override
	public RecipeMongoPO deleteRecipe(String recipeId) {
		return mongoTemplate.findAndRemove(new Query(Criteria.where("_id").is(recipeId)), RecipeMongoPO.class);
	}

}
