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

    @Query("MATCH (r:Recipe)-[ri:INCLUDE]->(i:Ingredient) " +
            "MATCH (i)-[rp:FORMED_BY]->(m)-[rn:REPRESENTED_BY]->(ln:LanguageName) " +
            "WHERE r.objectId = {id} " +
            "RETURN r,ri,i,rp,m,rn,ln")
    List<RecipePO> findByObjectId(@Param("id") String id);

    @Query("MATCH (r:Recipe) " +
            "WHERE r.objectId = {id} " +
            "RETURN count(r) > 0")
    boolean existsByObjectId(@Param("id") String id);
}
