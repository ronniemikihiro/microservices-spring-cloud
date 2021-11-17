package br.com.api.user.data.controller;

import br.com.commons.web.controller.CrudController;
import br.com.commons.web.exception.ResponseException;
import br.com.domain.entity.Permissao;
import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.service.CrudService;
import br.com.domain.service.PermissaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/permissao")
@Api("Serviços para gerenciar as permissões")
public class PermissaoController implements CrudController<Permissao, PermissaoDTO> {

	private final PermissaoService permissaoService;

	@Override
	public CrudService<Permissao, PermissaoDTO> getCrudService() {
		return permissaoService;
	}

	@GetMapping("listarPorPerfil/{idPerfil}")
	@ApiOperation("Lista todas as permissões vinculadas a um perfil")
	public ResponseEntity<Object> listarPorPerfil(@PathVariable Long idPerfil) {
		try {
			return ResponseEntity.ok().body(permissaoService.listarPorPerfil(idPerfil));
		} catch (ErrorException e) {
			return ResponseException.exception(e);
		}
	}

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

}