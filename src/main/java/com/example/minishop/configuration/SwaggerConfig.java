package com.example.minishop.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
// @EnableSwagger2
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi
                .builder()
                .group("Api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(
                        new Info()
                                .title("Bookstore Rest Api")
                                .description("Rest Api for bookstore web application")
                                .version("1.0")
                );
    }

    // @Bean
    // public Docket postsApi() {
    //     // return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
    //     //         .paths(postPaths()).build();

    //     return new Docket(DocumentationType.SWAGGER_2)
    //     .select()
    //     .apis(RequestHandlerSelectors.any())
    //     .paths(PathSelectors.any())
    //     .build();
    // }

    // @Override
    // public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //     registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

    //     registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    // }

    // private Predicate<String> postPaths() {
    //     return or(regex("/api/posts.*"), regex("/api/javainuse.*"));
    // }

    // private ApiInfo apiInfo() {
    //     return new ApiInfoBuilder().title("JavaInUse API").description("JavaInUse API reference for developers")
    //             .termsOfServiceUrl("http://javainuse.com").contact("javainuse@gmail.com").license("JavaInUse License")
    //             .licenseUrl("javainuse@gmail.com").version("1.0").build();
    // }

}
