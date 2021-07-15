package br.com.gateway.config;

import br.com.domain.entity.dto.UsuarioDTO;
import br.com.gateway.exception.InvalidTokenException;
import br.com.gateway.exception.IsInvalidTypeTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    public enum TypeToken {
        ACCESS, REFRESH;
    }

    public static final String ID_USUARIO = "id_usuario";
    public static final String USERNAME = "username";
    public static final String ID_PERFIL = "id_perfil";
    public static final String TYPE_TOKEN = "type_token";

    @Value("${jwt.expirationAccess}")
    private String expirationAccess;

    @Value("${jwt.expirationRefresh}")
    private String expirationRefresh;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public void isValidAccessToken(String token) throws InvalidTokenException {
        isValidToken(token, TypeToken.ACCESS);
    }

    public void isValidRefreshToken(String token) throws InvalidTokenException {
        isValidToken(token, TypeToken.REFRESH);
    }

    private void isValidToken(String token, TypeToken typeToken) throws InvalidTokenException {
        try {
            isTypeTokenAccess(token, typeToken);
        } catch (SignatureException e) {
            throw new InvalidTokenException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("JWT token is expired");
        } catch (UnsupportedJwtException e) {
            throw new InvalidTokenException("JWT token is unsupported");
        } catch (IllegalArgumentException e) {
            throw new InvalidTokenException("JWT claims string is empty");
        } catch (IsInvalidTypeTokenException e) {
            throw new InvalidTokenException(e.getMessage());
        } catch (Exception e) {
            throw new InvalidTokenException("JWT error");
        }
    }

    private void isTypeTokenAccess(String token, TypeToken typeToken) throws IsInvalidTypeTokenException {
        final Claims claims = getAllClaimsFromToken(token);
        final String type = claims.get(TYPE_TOKEN, String.class);
        if(StringUtils.isEmpty(type) || !typeToken.name().equals(type)) {
            throw new IsInvalidTypeTokenException("JWT Token is of type " + (TypeToken.ACCESS.equals(typeToken) ? TypeToken.REFRESH.name() : TypeToken.ACCESS.name()));
        }
    }

    public String generate(UsuarioDTO usuarioDTO, TypeToken typeToken) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(ID_USUARIO, usuarioDTO.getId());
        claims.put(USERNAME, usuarioDTO.getUsername());
        claims.put(ID_PERFIL, usuarioDTO.getPerfil().getId());
        claims.put(TYPE_TOKEN, typeToken);
        return doGenerateToken(claims, usuarioDTO.getUsername(), typeToken);
    }

    private String doGenerateToken(Map<String, Object> claims, String username, TypeToken typeToken) {
        final Long expirationSeconds = Long.valueOf(TypeToken.ACCESS.equals(typeToken) ? expirationAccess : expirationRefresh);
        final Long expirationTimeLong = expirationSeconds * 1000;

        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

}
