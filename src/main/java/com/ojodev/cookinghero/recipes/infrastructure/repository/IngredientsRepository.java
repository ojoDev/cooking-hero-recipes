package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.IngredientPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IngredientsRepository extends Neo4jRepository<IngredientPO, Long> {

    @Query("MATCH (i:Ingredient)-[rp:FORMED_BY]->(p)-[rln:REPRESENTED_BY]->(ln:LanguageName) RETURN i, rp, p, rln, ln")
    List<IngredientPO> findByObjectId(String objectId);
}
