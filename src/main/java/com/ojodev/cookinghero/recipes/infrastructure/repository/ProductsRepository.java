package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductsRepository extends Neo4jRepository<ProductPO, Long> {

    @Query("MATCH (p:Product) WITH p SKIP {offset} LIMIT {limit} " +
            "MATCH (p)-[r:REPRESENTED_BY]->(ln:LanguageName) " +
            "WHERE ({name} IS NULL OR ln.singular CONTAINS {name} OR ln.plural CONTAINS {name}) " +
            "AND ({language} IS NULL OR ln.language={language}) " +
            "AND ({status} IS NULL OR p.status = {status}) " +
            "RETURN p,r,ln " +
            "ORDER BY ln.singular")
    List<ProductPO> findProducts(@Param("name") String name, @Param("status") String status, @Param("language") String language, @Param("offset") int offset, @Param("limit") int limit);

    List<ProductPO> findByObjectId(String objectId);

    @Query("MATCH (p:Product)-[r:REPRESENTED_BY]->(ln:LanguageName) WHERE p.objectId={id} DETACH DELETE p,r,ln")
    void deleteById(@Param("id") String id);
}
