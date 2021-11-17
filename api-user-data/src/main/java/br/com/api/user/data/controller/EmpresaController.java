package br.com.api.user.data.controller;

import br.com.commons.web.controller.CrudController;
import br.com.domain.entity.Empresa;
import br.com.domain.entity.dto.EmpresaDTO;
import br.com.domain.service.CrudService;
import br.com.domain.service.EmpresaService;
import com.netflix.discovery.EurekaClient;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/empresa")
@Api("Serviços para gerenciar as empresas")
public class EmpresaController implements CrudController<Empresa, EmpresaDTO> {

	private final EmpresaService empresaService;
	private final EurekaClient eurekaClient;

	@Override
	public CrudService<Empresa, EmpresaDTO> getCrudService() {
		return empresaService;
	}

	@GetMapping("/instanceId")
	public String getInstanceId() {
		final String instanceId = eurekaClient.getApplicationInfoManager().getInfo().getInstanceId();
		log.info(instanceId);
		return instanceId;
	}

}