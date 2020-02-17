package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.config.Messages;
import com.ojodev.cookinghero.recipes.data.RecipesExamples;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesControllerDeleteRecipeTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private Messages messages;

	@MockBean
	private MongoTemplate mongoTemplate;
	
	@Test
	public void getRecipeById() throws Exception {
		initMongoRecipe();
		this.mvc.perform(
				delete("/recipes/{recipe-id}", RecipesExamples.RECIPE_ID_01).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void getRecipeByIdNotFound() throws Exception {
		initNoMongoRecipes();
		this.mvc.perform(delete("/recipes/{recipe-id}","0123456789ab0123456789cc").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void getRecipeOutOfMemoryException() throws Exception {
		initOutOfMemoryException();
		this.mvc.perform(delete("/recipes/{recipe-id}","0123456789ab0123456789cc").accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError())
		.andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
		.andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
	}
	
		
	private void initMongoRecipe() {
		when(this.mongoTemplate.findAndRemove(any(), eq(RecipePO.class))).thenReturn(RecipesExamples.RECIPE_01);
	}

	private void initNoMongoRecipes() {
		when(this.mongoTemplate.findAndRemove(any(), eq(RecipePO.class))).thenReturn(null);
	}

	private void initOutOfMemoryException() {
		when(this.mongoTemplate.findAndRemove(any(), eq(RecipePO.class))).thenThrow(new OutOfMemoryError());
	}
}
