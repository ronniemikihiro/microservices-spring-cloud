package br.com.api.user.controller;

import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.repository.UsuarioRepository;
import br.com.domain.service.UsuarioService;
import com.netflix.discovery.EurekaClient;
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
@RequestMapping("/usuario")
@Api("Serviços para gerenciar os usuários")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final UsuarioRepository usuarioRepository;
	private final EurekaClient eurekaClient;

	@GetMapping("/instanceId")
	public String getInstanceId() {
		final String instanceId = eurekaClient.getApplicationInfoManager().getInfo().getInstanceId();
		log.info(instanceId);
		return instanceId;
	}

	@GetMapping
	@ApiOperation("Lista todos usuários")
	public ResponseEntity<List<UsuarioDTO>> listarTodos() {
		return ResponseEntity.ok().body(usuarioRepository.listarTodos());
	}

	@GetMapping("{id}")
	@ApiOperation("Obtém um usuário por seu id")
	public ResponseEntity<UsuarioDTO> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(usuarioRepository.obterPorId(id));
	}

	@PostMapping
	@ApiOperation("Cria um novo usuário")
	public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO usuarioDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioDTO));
	}

	@PutMapping
	@ApiOperation("Altera os dados de um usuário")
	public ResponseEntity<UsuarioDTO> alterar(@RequestBody UsuarioDTO usuarioDTO) {
		return ResponseEntity.ok().body(usuarioService.salvar(usuarioDTO));
	}

	@DeleteMapping("{id}")
	@ApiOperation("Deleta um usuário")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}