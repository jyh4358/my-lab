package com.jddng.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi httpsDataBindingApi() {
    return GroupedOpenApi.builder()
        .group("Group 1")     // 그룹명(셀렉트박스)
        .pathsToMatch("/group1/**")
        .build();
  }

  @Bean
  public GroupedOpenApi adminApi() {
    return GroupedOpenApi.builder()
        .group("Group 2")
        .pathsToMatch("/group2/**")
        .addOpenApiCustomizer(addSecurityOpenApiCustomizer())
        .build();
  }

  @Bean
  public OpenAPI basicScheme() {
    return new OpenAPI()
        .components(new Components()
            .addSecuritySchemes("access-key1", new SecurityScheme()
                .type(Type.APIKEY)                      // 어떤 Type인지
                .in(In.HEADER)                        // header, query, cookie
                .name("access-key1")
                .description("엑세스 키1")
            )
            .addSecuritySchemes("access-key2", new SecurityScheme()
                .type(Type.APIKEY)                    // 어떤 Type인지
                .in(In.HEADER)                        // header, query, cookie
                .name("access-key2")
                .description("엑세스 키2")
            )
        );
  }
  public OpenApiCustomizer addSecurityOpenApiCustomizer() {
    return openApi -> openApi.addSecurityItem(new SecurityRequirement().addList("access-key1").addList("access-key2"))
        .info(new Info()
            .title("Swagger API")                   // Swagger 제목
            .description("Spring doc Group 1 API")  // Swagger 설명
            .version("v1.0.0")                      // Swagger 버전
            .license(
                new License().name("Apache 2.0").url("http://springdoc.org") // URL 링크 설정
            )
        )
        .addServersItem(new Server()                // Server 추가(server 추가 시 해당 server로 호출할 수 있도록 Swagger에서 컨트롤할 수 있다.)
            .url("http://localhost:8080")
            .description("Local development server"))
        .addServersItem(new Server()
            .url("https://api.example.com")
            .description("Production server"));
  }

  // 그룹이 1개일 경우 설정
//    @Bean
//    public OpenAPI springShopOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("SpringShop API")
//                        .description("Spring shop sample application")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("SpringShop Wiki Documentation")
//                        .url("https://springshop.wiki.github.org/docs"));
//    }
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes("access-key",
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("access-key"))
//                        .addSecuritySchemes("access-key2",
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("access-key2"))
//                        .addSecuritySchemes("access-key3",
//                                new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("access-key3")))
//                .security(List.of(new SecurityRequirement().addList("access-key")
//                        .addList("access-key2")
//                        .addList("access-key3"))
//                );
//
//    }
}
