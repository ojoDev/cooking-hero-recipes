package com.ojodev.cookinghero.recipes.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ojodev.cookinghero.recipes.po.RecipePO;

//DMS Another repository form, you can delete it
public interface RecipesRepositoryNotUsed extends MongoRepository<RecipePO, String> {
    // 
}