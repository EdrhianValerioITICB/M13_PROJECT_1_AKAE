package cat.iticbcn.demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(new Info()
                    .title("Companies and Offers API")
                    .description("API REST for empresas and ofertas")
                    .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                    .description("Wiki Docs")
                    .url("https://www.example.com/"));
    }
}
