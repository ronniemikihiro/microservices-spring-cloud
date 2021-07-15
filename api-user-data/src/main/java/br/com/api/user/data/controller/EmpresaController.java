package br.com.api.user.data.controller;

import br.com.domain.entity.dto.EmpresaDTO;
import br.com.domain.repository.EmpresaRepository;
import br.com.domain.service.EmpresaService;
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
@RequestMapping("/empresa")
@Api("Serviços para gerenciar as empresas")
public class EmpresaController {

	private final EmpresaService empresaService;
	private final EmpresaRepository empresaRepository;
	private final EurekaClient eurekaClient;

	@GetMapping("/instanceId")
	public String getInstanceId() {
		final String instanceId = eurekaClient.getApplicationInfoManager().getInfo().getInstanceId();
		log.info(instanceId);
		return instanceId;
	}

	@GetMapping
	@ApiOperation("Lista todas empresas")
	public ResponseEntity<List<EmpresaDTO>> listarTodos() {
		return ResponseEntity.ok().body(empresaRepository.listarTodos());
	}

	@GetMapping("{id}")
	@ApiOperation("Obtém uma empresa por seu id")
	public ResponseEntity<EmpresaDTO> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(empresaRepository.obterPorId(id));
	}

	@PostMapping
	@ApiOperation("Cria uma nova empresa")
	public ResponseEntity<EmpresaDTO> criar(@RequestBody EmpresaDTO empresaDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.salvar(empresaDTO));
	}

	@PutMapping
	@ApiOperation("Atualiza os dados de uma empresa")
	public ResponseEntity<EmpresaDTO> atualizar(@RequestBody EmpresaDTO empresaDTO) {
		return ResponseEntity.ok().body(empresaService.salvar(empresaDTO));
	}

	@DeleteMapping("{id}")
	@ApiOperation("Deleta uma empresa")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		empresaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}