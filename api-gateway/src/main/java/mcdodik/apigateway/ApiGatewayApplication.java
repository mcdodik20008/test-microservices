package mcdodik.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        var routes = builder.routes();

        routes.route("eureka-server-route", r -> r
                .path("/eureka/**")
                .filters(f -> f.stripPrefix(1))
                .uri("http://registry-eureka:8761")
        );

        return routes.build();
    }

}
