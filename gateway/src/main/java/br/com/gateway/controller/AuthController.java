package br.com.gateway.controller;

import br.com.commons.web.exception.ResponseException;
import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.exception.errors.AutenticacaoException;
import br.com.domain.repository.UsuarioRepository;
import br.com.domain.security.CryptoUtil;
import br.com.domain.util.Constantes;
import br.com.gateway.config.JwtUtil;
import br.com.gateway.entities.AuthRequestRefreshToken;
import br.com.gateway.entities.AuthRequestToken;
import br.com.gateway.entities.AuthResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> token(AuthRequestToken authRequestToken) {
        try {
            final UsuarioDTO usuarioDTO = usuarioRepository.obterPorUsername(authRequestToken.getUsername());

            if(usuarioDTO == null || !BCrypt.checkpw(authRequestToken.getPassword(), usuarioDTO.getSenha())) {
                throw new AutenticacaoException();
            }

            final String accessToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.ACCESS);
            final String refreshToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.REFRESH);

            return ResponseEntity.ok(new AuthResponse(usuarioDTO.getUsername(), accessToken, refreshToken));

        } catch (Exception e) {
            return ResponseException.exception(e);
        }
    }

    @PostMapping(value = "refreshToken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> refreshToken(AuthRequestRefreshToken authRequestRefreshToken) {
        try {
            jwtUtil.isValidRefreshToken(authRequestRefreshToken.getRefreshToken());

            final Claims claims = jwtUtil.getAllClaimsFromToken(authRequestRefreshToken.getRefreshToken());
            final String username = claims.get(Constantes.JWT_USERNAME, String.class);
            final UsuarioDTO usuarioDTO = usuarioRepository.obterPorUsername(username);

            final String accessToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.ACCESS);
            final String refreshToken = jwtUtil.generate(usuarioDTO, JwtUtil.TypeToken.REFRESH);

            return ResponseEntity.ok(new AuthResponse(usuarioDTO.getUsername(), accessToken, refreshToken));

        } catch (Exception e) {
            return ResponseException.exception(e);
        }
    }

    @GetMapping(value = "encrypt")
    public ResponseEntity<Object> encrypt(@RequestParam("plainText") String plainText) {
        try {
            return ResponseEntity.ok(CryptoUtil.encrypt(plainText));
        } catch (Exception e) {
            return ResponseException.exception(e);
        }
    }

    @GetMapping(value = "decrypt")
    public ResponseEntity<Object> decrypt(@RequestParam("hash") String hash) {
        try {
            return ResponseEntity.ok(CryptoUtil.decrypt(hash));
        } catch (Exception e) {
            return ResponseException.exception(e);
        }
    }

}
