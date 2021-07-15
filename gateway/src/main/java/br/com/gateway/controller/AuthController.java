package br.com.gateway.controller;

import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.repository.UsuarioRepository;
import br.com.gateway.entities.AuthRequestRefreshToken;
import br.com.gateway.entities.AuthRequestToken;
import br.com.gateway.entities.AuthResponse;
import br.com.gateway.config.JwtUtil;
import br.com.gateway.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> token(AuthRequestToken authRequestToken) {
        final UsuarioDTO usuarioDTO = usuarioRepository.obterPorUsername(authRequestToken.getUsername());

        if(usuarioDTO == null || !BCrypt.checkpw(authRequestToken.getPassword(), usuarioDTO.getSenha())) {
            return ResponseEntity.badRequest().body("Usuário ou senha inválido!");
        }

        final String accessToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.ACCESS);
        final String refreshToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.REFRESH);

        return ResponseEntity.ok(new AuthResponse(usuarioDTO.getUsername(), accessToken, refreshToken));
    }

    @PostMapping(value = "refreshToken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> refreshToken(AuthRequestRefreshToken authRequestRefreshToken) {
        try {
            jwtUtil.isValidRefreshToken(authRequestRefreshToken.getRefreshToken());
        } catch (InvalidTokenException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        final Claims claims = jwtUtil.getAllClaimsFromToken(authRequestRefreshToken.getRefreshToken());
        final String username = claims.get(JwtUtil.USERNAME, String.class);
        final UsuarioDTO usuarioDTO = usuarioRepository.obterPorUsername(username);

        final String accessToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.ACCESS);
        final String refreshToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.REFRESH);

        return ResponseEntity.ok(new AuthResponse(usuarioDTO.getUsername(), accessToken, refreshToken));
    }

}
