package br.com.api.user.controller;

import br.com.commons.web.controller.CrudController;
import br.com.domain.entity.Usuario;
import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.service.CrudService;
import br.com.domain.service.UsuarioService;
import com.netflix.discovery.EurekaClient;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/usuario")
@Api("Serviços para gerenciar os usuários")
public class UsuarioController implements CrudController<Usuario, UsuarioDTO> {

	private final HttpServletRequest request;
	private final UsuarioService usuarioService;
	private final EurekaClient eurekaClient;

	@Override
	public CrudService<Usuario, UsuarioDTO> getCrudService() {
		return usuarioService;
	}

	@GetMapping("/instanceId")
	public String getInstanceId() {
		final String instanceId = eurekaClient.getApplicationInfoManager().getInfo().getInstanceId();
		log.info(instanceId); request.getSession().setAttribute("username", request.getHeader("username"));
		log.info(request.getHeader("username"));
		return instanceId;
	}

}