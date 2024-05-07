package com.bsool.glasses.glassesshop.config;

import com.bsool.glasses.glassesshop.GlassesShopApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@Import({springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class})
@EnableSwagger2
public class SwaggerConfigurations {
    private final static String BASE_PACKAGE = GlassesShopApplication.class.getPackage().getName();

    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.application.description}")
    private String description;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                appName,
                description,
                "1",
                "",
                new Contact("", "", ""),
                "", "", Collections.emptyList());
    }
}
