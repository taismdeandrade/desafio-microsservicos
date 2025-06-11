package br.com.tais.cadastro.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    //Classe de configuração para o swagger
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Cadastro de pets.")
                                .description("Api para cadastro de pets.")
                                .contact(new Contact().name("Tais de Andrade").url("https://www.linkedin.com/in/taismdeandrade/"))
                );

    }
}
