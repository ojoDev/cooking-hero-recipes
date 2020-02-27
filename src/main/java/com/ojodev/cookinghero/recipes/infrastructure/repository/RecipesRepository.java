package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecipesRepository extends Neo4jRepository<RecipePO, Long> {

    List<RecipePO> findByObjectId(String objectId);

}
