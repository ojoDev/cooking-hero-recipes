package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StepsRepository extends Neo4jRepository<StepPO, Long> {

    @Query("MATCH (r:Recipe)-[ri:INCLUDE]->(s:Step) "+
            "WHERE r.objectId = {recipeId} "+
            "AND s.objectId = {stepId} "+
            "OPTIONAL MATCH (s)-[rr:REPRESENTED_BY]->(m:Media) " +
            "RETURN s,rr,m,r,ri")
    List<StepPO> findStepInRecipe(@Param("recipeId") String recipeId, @Param("stepId") String stepId);

    @Query("MATCH (r:Recipe)-[ri:INCLUDE]->(s:Step) "+
            "WHERE r.objectId = {recipeId} "+
            "OPTIONAL MATCH (s)-[rr:REPRESENTED_BY]->(m:Media) " +
            "RETURN s,rr,m,r,ri")
    List<StepPO> findStepsInRecipe(@Param("recipeId") String recipeId);
}
