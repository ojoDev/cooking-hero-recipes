package com.ojodev.cookinghero.recipes.infrastructure.po;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity(label = "Product")
public class ProductPO {

    @Id
    @GeneratedValue
    private Long id;

    private String objectId;

    @Relationship(type = "REPRESENTED_BY")
    private List<DescriptiveNamePO> names;

    private String status;

    public ProductPO() {
    }

    public ProductPO(String objectId, List<DescriptiveNamePO> names, String status) {
        this.objectId = objectId;
        this.names = names;
        this.status = status;
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

    public List<DescriptiveNamePO> getNames() {
        return names;
    }

    public void setNames(List<DescriptiveNamePO> names) {
        this.names = names;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
