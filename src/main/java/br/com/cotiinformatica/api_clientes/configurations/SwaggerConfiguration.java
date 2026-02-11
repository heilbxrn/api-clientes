package br.com.cotiinformatica.api_clientes.configurations;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    OpenAPI customOpenApi() {
        var openAPI = new OpenAPI()
                .info(new Info()
                        .title("Clientes API - COTI Informática")
                        .description("API Spring Boot para gerenciamento de clientes")
                        .version("v1"));

        return openAPI;
    }

}
