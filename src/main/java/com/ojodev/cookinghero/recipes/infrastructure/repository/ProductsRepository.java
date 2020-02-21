package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductsRepository extends Neo4jRepository<ProductPO, Long> {

    List<ProductPO> findAll();

    List<ProductPO> findByObjectId(String objectId);

    @Query("MATCH (p:Product)-[r:REPRESENTED_BY]->(ln:LanguageName) WHERE p.objectId={id} DETACH DELETE p,r,ln")
    void deleteById(@Param("id") String id);
}
