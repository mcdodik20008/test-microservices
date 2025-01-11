package mcdodik.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    @ConditionalOnProperty(prefix = "spring.application", name = "name", havingValue = "api-gateway")
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        var routes = builder.routes();

        routes
                .route("eureka-server-route", r -> r
                        .path("/api/eureka/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://registry-eureka:8761"))
                .route("animal-route", r -> r
                        .path("/api/animal/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://animal:8081/animal/")
                );

        return routes.build();
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.application", name = "name", havingValue = "api-gateway-local")
    public RouteLocator customRouteLocatorLocal(RouteLocatorBuilder builder) {
        var routes = builder.routes();

        routes
                .route("eureka-server-route", r -> r
                        .path("/api/eureka/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8761")
                ).route("animal-route", r -> r
                        .path("/api/animal/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8081")
                );

        return routes.build();
    }

}
