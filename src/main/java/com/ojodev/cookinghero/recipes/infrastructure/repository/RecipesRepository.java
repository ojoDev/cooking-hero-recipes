package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.RecipePO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecipesRepository extends Neo4jRepository<RecipePO, Long> {

    @Query("MATCH (r:Recipe) " +
            "WHERE r.objectId = {id} " +
            "OPTIONAL MATCH (r)-[ri:INCLUDE]->(i:Ingredient)-[rp:FORMED_BY]->(m)-[rn:REPRESENTED_BY]->(ln:LanguageName) " +
            "RETURN r,ri,i,rp,m,rn,ln")
    List<RecipePO> findByObjectIdBasic(@Param("id") String id);

    @Query("MATCH (r:Recipe) "+
            "WHERE r.objectId = {id} " +
            "OPTIONAL MATCH (r)-[ri:INCLUDE]->(i:Ingredient) "+
            "OPTIONAL MATCH (i)-[rp:FORMED_BY]->(m)-[rn:REPRESENTED_BY]->(ln:LanguageName) "+
            "OPTIONAL MATCH (r)-[rb:REPRESENTED_BY]->(s) "+
            "OPTIONAL MATCH (r)-[ri2:INCLUDE]->(c:CuisineType)-[rb2:REPRESENTED_BY]->(ln2:LanguageName) "+
            "RETURN r,ri,i,rp,m,rn,ln,rb,s,ri2,c,rb2,ln2")
    List<RecipePO> findByObjectId(@Param("id") String id);

    @Query("MATCH (r:Recipe) " +
            "WHERE r.objectId = {id} " +
            "RETURN count(r) > 0")
    boolean existsByObjectId(@Param("id") String id);
}
