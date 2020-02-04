package com.ojodev.cookinghero.recipes.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages= "com.ojodev")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Override
    protected String getDatabaseName() {
        return "cookinghero";
    }
  
    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host, Integer.valueOf(port));
    }
  
    @Override
    protected String getMappingBasePackage() {
        return "com.ojodev";
    }
}