package com.ojodev.cookinghero.recipes.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ojodev.cookinghero.recipes.bean.Recipe;
import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.converter.RecipeConverter;
import com.ojodev.cookinghero.recipes.dao.RecipesRepository;
import com.ojodev.cookinghero.recipes.exception.NotFoundException;
import com.ojodev.cookinghero.recipes.po.RecipePO;


@Component
public class RecipesBusiness {
	
	@Autowired
	private RecipesRepository recipesRepository;
	
	@Autowired
	private RecipeConverter recipeConverter;
	
	@Autowired
	private Messages messages;

	public Recipe getRecipe(String recipeId) throws NotFoundException {
		RecipePO recipePO = recipesRepository.findRecipe(recipeId);
       if (recipePO==null || recipePO.getId() == null) {
        	throw new NotFoundException(messages.get("error.notfound.code"), messages.get("error.notfound.desc"));
        }
		return recipeConverter.toRecipe(recipePO);
	}

	
	public List<Recipe> getRecipes() {
		//Primera forma busqueda
		List<RecipePO> recipesPOList =recipesRepository.findRecipes();
		return recipeConverter.toRecipes(recipesPOList);
		//Segunda forma busqueda:
		//List<RecipePO> tempList = recipesDAO.findRecipes();
		
		//TODO DMS Prueba de recuperar recipes
//		MongoClient client = new MongoClient("localhost",27017);
//		MongoDatabase db = client.getDatabase("cookinghero");
//		MongoCollection<Document> coll = db.getCollection("recipes");
//		List<Document> all = coll.find().into(new ArrayList<Document>());
//		//TODO DMS Hacer conversor, de momento vacio
		//return new ArrayList<Recipe>();
	}
	
	public void addRecipe(Recipe recipe) {
		recipesRepository.addRecipe(recipeConverter.toRecipePO(recipe));
		
		//TODO DMS: Prueba de inserción de recipe en BBDD
//		MongoClient client = new MongoClient("localhost",27017);
//		MongoDatabase db = client.getDatabase("cookinghero");
//		MongoCollection<Document> coll = db.getCollection("recipes");
//		Document doc = new Document("name", recipe.getName())
//				.append("description", recipe.getDescription())
//				.append("cousine_type", recipe.getCousineType())
//				.append("length", recipe.getLength())
//				.append("difficulty", recipe.getDifficulty());
//		coll.insertOne(doc);
//		client.close();
	}
	
	public void deleteRecipe(String recipeId) throws NotFoundException {
		RecipePO recipe = recipesRepository.deleteRecipe(recipeId);
		if (recipe == null) {
			throw new NotFoundException(messages.get("error.notfound.code"),messages.get("error.notfound.desc"));
		}
	}
	
	  
}
