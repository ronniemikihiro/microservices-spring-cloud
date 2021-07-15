package br.com.api.user.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.api.user.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Microserviços")
                .description("Microserviços")
                .version("1.0")
                .contact(new Contact("Ronnie Mikihiro Sato Lopes", "https://www.linkedin.com/in/ronnie-mikihiro-sato-lopes-801432a0/", "ronnielopes@hotmail.com"))
                .license("Private for me")
                .licenseUrl("https://www.linkedin.com/in/ronnie-mikihiro-sato-lopes-801432a0/")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

   private List<SecurityReference> defaultAuth() {
       final AuthorizationScope[] authorizationScopes = { new AuthorizationScope("global", "accessEverything") };
       return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
   }

}
