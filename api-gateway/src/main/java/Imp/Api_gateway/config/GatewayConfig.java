package Imp.Api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {

        return builder.routes()

             /*
                .route("order-service",
                        r -> r.path("/orders/**")
                                .uri("http://localhost:8081"))

                .route("user-service",
                        r -> r.path("/users/**")
                                .uri("http://localhost:8080"))

                .route("product-service",
                        r -> r.path("/products/**")
                                .uri("http://localhost:8082"))

               */
                .route("user-service",
                        r -> r.path("/users/**")
                                .uri("lb://USER-SERVICE"))

                .route("product-service",
                        r -> r.path("/products/**")
                                .uri("lb://PRODUCT-SERVICE"))

                .route("order-service",
                        r -> r.path("/orders/**")
                                .uri("lb://ORDER-SERVICE"))

                .build();
    }
}