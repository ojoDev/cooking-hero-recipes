package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeasuresRepository extends Neo4jRepository<MeasurePO, Long> {

    List<MeasurePO> findAll();

    List<MeasurePO> findByObjectId(String objectId);

    @Query("MATCH (m:Measure)-[r:REPRESENTED_BY]->(ln:LanguageName) " +
            "WHERE m.objectId={id} " +
            "DETACH DELETE m,r,ln")
    void deleteById(@Param("id") String id);
}
