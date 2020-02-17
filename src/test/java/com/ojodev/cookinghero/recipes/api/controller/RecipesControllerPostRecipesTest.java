package com.ojodev.cookinghero.recipes.api.controller;

import com.ojodev.cookinghero.recipes.data.RecipesExamples;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import com.ojodev.cookinghero.recipes.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipesControllerPostRecipesTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MongoTemplate mongoTemplate;
	
	@Test
	public void postRecipe() throws Exception {
		initRecipeInserted();
		this.mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(RecipesExamples.RECIPE_REQUEST))).andExpect(status().isCreated());
	}
	
	//TODO DMS dont work, why?
//	@Test
//	public void postRecipeOutOfMemoryException() throws Exception {
//		initOutOfMemoryException();
//		this.mvc.perform(post("/recipes").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
//				.content(TestUtils.asJsonString(RecipesExamples.RECIPE_REQUEST))).andExpect(status().isInternalServerError())
//		.andExpect(jsonPath("$.code", is(messages.get("error.server.code"))))
//		.andExpect(jsonPath("$.description", is(messages.get("error.server.desc"))));
//	}
	
		
	private void initRecipeInserted() {
		 Mockito.doAnswer(invocation -> {
		        return RecipesExamples.RECIPE_01;
		    }).when(mongoTemplate).insert(RecipePO.class);
		//when(this.mongoTemplate.insert(eq(RecipePO.class))).thenReturn(RecipesExamples.RECIPE_01);
	}

	private void initOutOfMemoryException() {
		 Mockito.doThrow(new OutOfMemoryError()).when(mongoTemplate).insert(RecipePO.class);
	}
}
