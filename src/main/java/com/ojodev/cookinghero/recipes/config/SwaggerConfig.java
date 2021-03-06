package com.ojodev.cookinghero.recipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig { 
	
	private static final String BASE_PACKAGE = "com.ojodev.cookinghero.recipes.api.controller";


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(Optional.class)
                .apiInfo(apiEndPointsInfo());
    }



    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Cooking Hero - Recipes API")
                .description("API for manage hero recipes in Cooking Hero app.\nAs a **Hero**, you can manage your recipes with recipes tag methods.\nAs a **Admin**, you can manage all data related to recipes and control the Cooking World.")
                //.contact(new Contact("Ojodev", "https://github.com/ojoDev", "davidmunozsantos@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }


}