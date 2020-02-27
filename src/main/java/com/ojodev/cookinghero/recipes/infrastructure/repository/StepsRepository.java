package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.ProductPO;
import com.ojodev.cookinghero.recipes.infrastructure.po.StepPO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StepsRepository extends Neo4jRepository<StepPO, Long> {

    List<StepPO> findByObjectId(String objectId);
}
