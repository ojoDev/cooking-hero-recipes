package com.ojodev.cookinghero.recipes.infrastructure.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@Data
@NodeEntity(label="CuisineType")
public class CuisineTypePO {

    @Id
    @GeneratedValue
    private Long id;

    public CuisineTypePO() {
    }

    public CuisineTypePO(String objectId, List<LanguageNamePO> names) {
        this.objectId = objectId;
        this.names = names;
    }

    public String objectId;

    @Relationship(type = "REPRESENTED_BY")
    public List<LanguageNamePO> names;

}
