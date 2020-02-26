package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CuisineTypesRepository  extends Neo4jRepository<CuisineTypePO, Long> {

    List<CuisineTypePO> findAll();

    @Query("MATCH (c:CuisineType)-[r:REPRESENTED_BY]->(ln:LanguageName) " +
            "WHERE c.objectId = {id} " +
            "RETURN c,r,ln " +
            "ORDER BY ln.objectId DESC")
    CuisineTypePO findById(@Param("id") String id);

    @Query("MATCH (c:CuisineType)-[r:REPRESENTED_BY]->(ln:LanguageName) " +
            "WHERE ln.language={language} AND ln.name CONTAINS {name} " +
            "RETURN c,r,ln ORDER BY ln.name DESC")
    List<CuisineTypePO> findByName(@Param("name") String name, @Param("language") String language);

    @Query("MATCH (c:CuisineType)-[r:REPRESENTED_BY]->(ln:LanguageName) " +
            "WHERE c.objectId={id} " +
            "DETACH DELETE c,r,ln")
    void deleteById(@Param("id") String id);


}
