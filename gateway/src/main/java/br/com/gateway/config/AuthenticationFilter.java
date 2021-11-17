package br.com.gateway.config;

import br.com.domain.exception.errors.ErrorException;
import br.com.domain.util.Constantes;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@RefreshScope
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationFilter implements GatewayFilter {

    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return this.onError(exchange, "Authorization header is missing in request");
            }

            try {
                final String token = this.extractToken(request);
                jwtUtil.isValidAccessToken(token);
                this.populateRequestWithHeaders(exchange, token);
            } catch (ErrorException e) {
                return this.onError(exchange, e.getMessage());
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error) {
        final byte[] bytes = error.getBytes(StandardCharsets.UTF_8);
        final DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);

        final ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Flux.just(buffer));
    }

    private String extractToken(ServerHttpRequest request) {
        String token = null;

        final String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isNotEmpty(bearerToken)) {
            final String[] parts = bearerToken.split(" ");
            token = parts[parts.length - 1].trim();
        }

        return token;
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) throws ErrorException {
        final Claims claims = jwtUtil.getAllClaimsFromToken(token);

        exchange.getRequest().mutate()
                .header(Constantes.JWT_ID_USUARIO, String.valueOf(claims.get(Constantes.JWT_ID_USUARIO)))
                .header(Constantes.JWT_USERNAME, String.valueOf(claims.get(Constantes.JWT_USERNAME)))
                .build();
    }

}
