package org.example.restapi2.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:7071");
        server.setDescription("Event Ticketing System");

        Info information = new Info()
                .title("Online Learning Platform")
                .version("1.0")
                .description("Efficiently manage events with our streamlined ticketing system.");
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
