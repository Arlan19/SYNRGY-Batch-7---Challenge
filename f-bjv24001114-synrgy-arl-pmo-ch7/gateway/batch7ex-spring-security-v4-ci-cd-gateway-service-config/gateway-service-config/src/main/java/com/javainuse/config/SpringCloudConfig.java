package com.javainuse.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

//    referensi https://spring.io/projects/spring-cloud-gateway
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route(r -> r.path("/api/**")// localhost:8080/employee
//                        .uri("http://localhost:8080/")// mengarah
//                        .id("employeeModule"))
//
//                .route(r -> r.path("/api/v2/**")//localhost:8080/consumer
//                        .uri("http://localhost:8081/")
//                        .id("consumerModule"))
//                .route(r -> r.path("/api/v3/**")//localhost:8080/consumer
//                        .uri("http://localhost:8084/")
//                        .id("reportModule"))
                .route(r -> r.path("/api/v3/**")//localhost:8080/consumer
                        .uri("http://localhost:8085/")
                        .id("masterModule"))
                .route(r -> r.path("/api/**")// Users
                        .uri("http://localhost:8085/")// mengarah
                        .id("employeeModule"))
                .route(r -> r.path("/api/**")// Order dan OrderDetail
                        .uri("http://localhost:8086/")// mengarah
                        .id("employeeModule"))
                .route(r -> r.path("/api/**")// MerchantProduct
                        .uri("http://localhost:8087/")// mengarah
                        .id("employeeModule"))
                .route(r -> r.path("/api/**")//  eureka
                        .uri("http://localhost:8761/")// mengarah
                        .id("employeeModule"))
                .build();
    }

}