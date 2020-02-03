package com.ojodev.cookinghero.recipes.api;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.IngredientsExamples;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.data.StepsExamples;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesControllerGetRecipesTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private Messages messages;

	@MockBean
	private MongoTemplate mongoTemplate;
	
	
	@Test
	public void getRecipes() throws Exception {
		initTwoMongoRecipes();
		this.mvc.perform(get("/recipes").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].id", is(RecipesExamples.RECIPE_ID_01.toString())))
				.andExpect(jsonPath("$[0].name", is(RecipesExamples.RECIPE_NAME_01)))
				.andExpect(jsonPath("$[0].description", is(RecipesExamples.RECIPE_DESCRIPTION_01)))
				.andExpect(jsonPath("$[0].cuisine-type[0]", is(RecipesExamples.RECIPE_01_CUISINE_TYPE_01)))
				.andExpect(jsonPath("$[0].cuisine-type[1]", is(RecipesExamples.RECIPE_01_CUISINE_TYPE_02)))
				.andExpect(jsonPath("$[0].preparation-time", is(Integer.valueOf(RecipesExamples.RECIPE_PREPARATION_TIME_01.toString()))))
				.andExpect(jsonPath("$[0].difficulty", is(Integer.valueOf(RecipesExamples.RECIPE_DIFFICULTY_01.toString()))))
				.andExpect(jsonPath("$[0].photo.href", containsString(RecipesExamples.RECIPE_PHOTO_HREF_01)))
				.andExpect(jsonPath("$[0].steps[0].description", is(StepsExamples.STEP_01_DESCRIPTION)))
				.andExpect(jsonPath("$[0].steps[0].time", is(Integer.valueOf(StepsExamples.STEP_01_TIME.toString()))))
				.andExpect(jsonPath("$[0].steps[1].description", is(StepsExamples.STEP_02_DESCRIPTION)))
				.andExpect(jsonPath("$[0].steps[1].time", is(Integer.valueOf(StepsExamples.STEP_02_TIME.toString()))))
				.andExpect(jsonPath("$[0].steps[2].description", is(StepsExamples.STEP_03_DESCRIPTION)))
				.andExpect(jsonPath("$[0].steps[2].time").doesNotExist())
				.andExpect(jsonPath("$[0].ingredients[0].product", is(IngredientsExamples.INGREDIENT_01_PRODUCT)))
				.andExpect(jsonPath("$[0].ingredients[0].quantity", is(IngredientsExamples.INGREDIENT_01_QUANTITY)))
				.andExpect(jsonPath("$[0].ingredients[0].measure", is(IngredientsExamples.INGREDIENT_01_MEASURE)))
				.andExpect(jsonPath("$[0].ingredients[1].product", is(IngredientsExamples.INGREDIENT_02_PRODUCT)))
				.andExpect(jsonPath("$[0].ingredients[1].quantity", is(IngredientsExamples.INGREDIENT_02_QUANTITY)))
				.andExpect(jsonPath("$[0].ingredients[1].measure", is(IngredientsExamples.INGREDIENT_02_MEASURE)))
				.andExpect(jsonPath("$[0].ingredients[2].product", is(IngredientsExamples.INGREDIENT_03_PRODUCT)))
				.andExpect(jsonPath("$[0].ingredients[2].quantity", is(IngredientsExamples.INGREDIENT_03_QUANTITY)))
				.andExpect(jsonPath("$[0].ingredients[2].measure", is(IngredientsExamples.INGREDIENT_03_MEASURE)))
				.andExpect(jsonPath("$[0].ingredients[3].product", is(IngredientsExamples.INGREDIENT_04_PRODUCT)))
				.andExpect(jsonPath("$[0].ingredients[3].quantity").doesNotExist())
				.andExpect(jsonPath("$[0].ingredients[3].measure").doesNotExist())
				.andExpect(jsonPath("$[0].ingredients[4].product", is(IngredientsExamples.INGREDIENT_05_PRODUCT)))
				.andExpect(jsonPath("$[0].ingredients[4].quantity").doesNotExist())
				.andExpect(jsonPath("$[0].ingredients[4].measure").doesNotExist())
				.andExpect(jsonPath("$[0].user", is(RecipesExamples.RECIPE_USER_01)))
				.andExpect(jsonPath("$[0].creationDate", is(RecipesExamples.RECIPE_CREATION_DATE_01_STRING)))
				.andExpect(jsonPath("$[1].id", is(RecipesExamples.RECIPE_ID_02.toString())))
				.andExpect(jsonPath("$[1].name", is(RecipesExamples.RECIPE_NAME_02)))
				.andExpect(jsonPath("$[1].description", is(RecipesExamples.RECIPE_DESCRIPTION_02)));
	}
	
	@Test
	public void getRecipesWithParam() throws Exception {
		initOneMongoRecipe();
		this.mvc.perform(get("/recipes").param("name", RecipesExamples.RECIPE_NAME_02).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()", is(1)))
				.andExpect(jsonPath("$[0].id", is(RecipesExamples.RECIPE_ID_02.toString())))
				.andExpect(jsonPath("$[0].name", is(RecipesExamples.RECIPE_NAME_02)))
				.andExpect(jsonPath("$[0].description", is(RecipesExamples.RECIPE_DESCRIPTION_02)));
	}
	

	@Test
	public void getRecipesNotFound() throws Exception {
		initNoMongoRecipes();
		this.mvc.perform(get("/recipes").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	
	@Test
	public void getRecipesWithNameNotFound() throws Exception {
		initNoMongoRecipes();
		this.mvc.perform(get("/recipes").param("name", "xxxxxxxxx").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	
	@Test
	public void getRecipesOutOfMemoryException() throws Exception {
		initOutOfMemoryException();
		this.mvc.perform(get("/recipes").param("name", "xxxxxxxxx").accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError())
		.andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
		.andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
	}
	
	
	
	private void initTwoMongoRecipes() {
		when(this.mongoTemplate.find(any(), eq(RecipePO.class))).thenReturn(RecipesExamples.RECIPE_LIST_TWO_RECIPES);
	}

	private void initOneMongoRecipe() {
		when(this.mongoTemplate.find(any(), eq(RecipePO.class))).thenReturn(RecipesExamples.RECIPE_LIST_ONE_RECIPE);
	}

	private void initNoMongoRecipes() {
		when(this.mongoTemplate.find(any(), eq(RecipePO.class))).thenReturn(new ArrayList<RecipePO>());
	}

	private void initOutOfMemoryException() {
		when(this.mongoTemplate.find(any(), eq(RecipePO.class))).thenThrow(new OutOfMemoryError());
	}
}
