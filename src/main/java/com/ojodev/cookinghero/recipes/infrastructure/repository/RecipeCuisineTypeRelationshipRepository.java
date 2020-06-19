package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.RecipeCuisineTypeRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeCuisineTypeRelationshipRepository extends Neo4jRepository<RecipeCuisineTypeRelationship, Long> {


}
