package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.infrastructure.po.MeasurePO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MeasuresRepository extends Neo4jRepository<MeasurePO, Long> {

    List<MeasurePO> findAll();

}
