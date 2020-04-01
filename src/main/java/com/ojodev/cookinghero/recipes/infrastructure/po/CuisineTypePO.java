package com.ojodev.cookinghero.recipes.infrastructure.po;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity(label="CuisineType")
public class CuisineTypePO {

    @Id
    @GeneratedValue
    private Long id;

    private String objectId;

    @Relationship(type = "REPRESENTED_BY")
    private List<LanguageNamePO> names;

    public CuisineTypePO() {
    }

    public CuisineTypePO(String objectId, List<LanguageNamePO> names) {
        this.objectId = objectId;
        this.names = names;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<LanguageNamePO> getNames() {
        return names;
    }

    public void setNames(List<LanguageNamePO> names) {
        this.names = names;
    }


}
