package com.ojodev.cookinghero.recipes.infrastructure.repository;

import com.ojodev.cookinghero.recipes.api.model.LanguageEnum;
import com.ojodev.cookinghero.recipes.domain.model.CuisineTypeBO;
import com.ojodev.cookinghero.recipes.infrastructure.po.CuisineTypePO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CuisineTypesRepository  extends Neo4jRepository<CuisineTypePO, Long> {

    List<CuisineTypePO> findAll();

    CuisineTypePO findById(String id);

    List<CuisineTypePO> findByNameENContaining(@Param("name:en") String name);

    List<CuisineTypePO> findByNameESContaining(@Param("name:es") String name);



}
