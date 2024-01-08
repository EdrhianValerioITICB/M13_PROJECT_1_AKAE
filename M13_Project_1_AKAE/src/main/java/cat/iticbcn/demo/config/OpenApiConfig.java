package cat.iticbcn.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(new Info()
                    .title("Companies and Offers API")
                    .description("API REST for empresas and ofertas")
                    .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                    .description("Wiki Docs")
                    .url("https://www.example.com/"))
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()));
    }
}
