package br.com.api.user.data.controller;

import br.com.commons.web.exception.ResponseException;
import br.com.domain.enums.EnumTipoAutenticacao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/tipoAutenticacao")
@Api("Serviços para gerenciar os tipos de autenticação")
public class TipoAutenticacaoController {

    @GetMapping
    @ApiOperation("Listar todos tipos de autenticação")
    public ResponseEntity<Object> listar() {
        try {
            return ResponseEntity.ok().body(EnumTipoAutenticacao.values());
        } catch (Exception e) {
            return ResponseException.exception(e);
        }
    }

}