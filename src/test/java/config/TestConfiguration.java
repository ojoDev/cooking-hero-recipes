package config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//TODO DMS: Hay que conseguir que la BBDD de Neo4j de pruebas sea temporal
@Configuration
@EnableNeo4jRepositories("com.ojodev.cookinghero.recipes.infrastructure")
@EnableTransactionManagement
@ComponentScan("com.ojodev.cookinghero")
//@AutoConfigurationPackage
public class TestConfiguration {

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration.Builder builder = new org.neo4j.ogm.config.Configuration.Builder();
        return builder.build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "com.ojodev.cookinghero");
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

}