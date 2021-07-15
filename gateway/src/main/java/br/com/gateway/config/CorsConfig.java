package br.com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class CorsConfig {

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            final ServerHttpRequest request = ctx.getRequest();

            if (CorsUtils.isCorsRequest(request)) {
                final ServerHttpResponse response = ctx.getResponse();
                final HttpHeaders headers = response.getHeaders();

                headers.add("Access-Control-Allow-Origin", "*");
                headers.add("Access-Control-Allow-Credentials", "true");
                headers.add("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
                headers.add("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
                headers.add("Access-Control-Max-Age", "3600");

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                } else {
                    return chain.filter(ctx);
                }
            }
            return chain.filter(ctx);
        };
    }

}
