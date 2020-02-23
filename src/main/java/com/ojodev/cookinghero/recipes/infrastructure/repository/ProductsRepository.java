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

    /**
     * Find products by name.
     *
     * @param name product name. Use "" if you dont need search by a name.
     * @param limit limit of records to return
     * @param skip skip records
     * @return list of products. Empty list if no match.
     */
    @Query("MATCH (p:Product) WITH p SKIP {skip} LIMIT {limit} MATCH (p)-[r:REPRESENTED_BY]->(ln:LanguageName) WHERE ln.singular CONTAINS {name} AND ln.language={language} RETURN p,r,ln")
    List<ProductPO> findByName(String name, String language, int limit, int skip);

    @Query("MATCH (p:Product) WITH p MATCH (p)-[r:REPRESENTED_BY]->(ln:LanguageName) WHERE ln.singular CONTAINS {name} AND ln.language={language} RETURN count(p)")
    Long countByName(String name, String language);

    List<ProductPO> findByObjectId(String objectId);

    @Query("MATCH (p:Product)-[r:REPRESENTED_BY]->(ln:LanguageName) WHERE p.objectId={id} DETACH DELETE p,r,ln")
    void deleteById(@Param("id") String id);
}
