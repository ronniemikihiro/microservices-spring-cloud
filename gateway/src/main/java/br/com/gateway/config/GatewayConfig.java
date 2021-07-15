package br.com.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@EnableHystrix
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        final Function<GatewayFilterSpec, UriSpec> filter = f -> f.filter(authenticationFilter).retry(1);

        return builder.routes()
                .route("apiUserModule", r -> r.path("/api-user/**")
                        .filters(filter)
                        .uri("lb://api-user"))

                .route("apiUserDataModule", r -> r.path("/api-user-data/**")
                        .filters(filter)
                        .uri("lb://api-user-data"))

                .build();
    }
}
