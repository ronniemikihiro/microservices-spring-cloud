package br.com.api.user.data.controller;

import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.repository.PermissaoRepository;
import br.com.domain.service.PermissaoService;
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
@RequestMapping("/permissao")
@Api("Serviços para gerenciar as permissões")
public class PermissaoController {

	private final PermissaoService permissaoService;
	private final PermissaoRepository permissaoRepository;

//	@GetMapping
//	@ApiOperation(value = "List all available permissões paginated")
//	public Page<PermissaoDTO> searchPaginated(
//			@RequestParam(value = "page", defaultValue = "0") Integer page,
//			@RequestParam(value = "size", defaultValue = "5") Integer size,
//			@RequestParam(value = "orderProperty", defaultValue = "nome") String orderProperty,
//			@RequestParam(value = "orderDirection", defaultValue = "asc") String orderDirection,
//			@RequestParam(value = "filterField", required = false) String filterField,
//			@RequestParam(value = "filterValue", required = false) String filterValue) {
//		final PropertiesDatatable propertiesDatatable = new PropertiesDatatable(page, size, orderProperty, orderDirection, filterField, filterValue);
//		return permissaoService.searchByNomePaginated(propertiesDatatable);
//	}

	@GetMapping
	@ApiOperation("Lista todas permissões")
	public ResponseEntity<List<PermissaoDTO>> searchAll() {
		return ResponseEntity.ok().body(permissaoRepository.listarTodos());
	}

	@GetMapping("{id}")
	@ApiOperation("Obtém uma permissão por seu id")
	public ResponseEntity<PermissaoDTO> obterPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(permissaoRepository.obterPorId(id));
	}

	@PostMapping
	@ApiOperation("Cria uma nova permissão")
	public ResponseEntity<PermissaoDTO> create(@RequestBody PermissaoDTO permissaoDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(permissaoService.salvar(permissaoDTO));
	}

	@PutMapping
	@ApiOperation("Atualiza os dados de uma permissão")
	public ResponseEntity<PermissaoDTO> update(@RequestBody PermissaoDTO permissaoDTO) {
		return ResponseEntity.ok().body(permissaoService.salvar(permissaoDTO));
	}

	@DeleteMapping("{id}")
	@ApiOperation("Deleta uma permissão")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		permissaoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("listarPorPerfil/{idPerfil}")
	@ApiOperation("Lista todas as permissões vinculadas a um perfil")
	public ResponseEntity<List<PermissaoDTO>> listarPorPerfil(@PathVariable Long idPerfil) {
		return ResponseEntity.ok().body(permissaoService.listarPorPerfil(idPerfil));
	}

}