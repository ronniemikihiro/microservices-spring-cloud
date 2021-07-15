package br.com.api.user.data.controller;

import br.com.domain.entity.dto.PerfilDTO;
import br.com.domain.repository.PerfilRepository;
import br.com.domain.service.PerfilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/perfil")
@Api("Serviços para gerenciar os perfis")
public class PerfilController {
    
    private final PerfilService perfilService;
    private final PerfilRepository perfilRepository;

    @GetMapping
    @ApiOperation("Lista todos perfis")
    public ResponseEntity<List<PerfilDTO>> listarTodos() {
        return ResponseEntity.ok().body(perfilRepository.listarTodos());
    }

    @GetMapping("{id}")
    @ApiOperation("Obtém um perfil por seu id")
    public ResponseEntity<PerfilDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(perfilRepository.obterPorId(id));
    }

    @PostMapping
    @ApiOperation("Cria um novo perfil")
    public ResponseEntity<PerfilDTO> criar(@RequestBody PerfilDTO perfilDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilService.salvar(perfilDTO));
    }

    @PutMapping
    @ApiOperation("Atualiza os dados de um perfil")
    public ResponseEntity<PerfilDTO> atualizar(@RequestBody PerfilDTO perfilDTO) {
        return ResponseEntity.ok().body(perfilService.salvar(perfilDTO));
    }

    @DeleteMapping("{id}")
    @ApiOperation("Deleta um perfil")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        perfilRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
