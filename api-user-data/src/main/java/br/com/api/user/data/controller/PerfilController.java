package br.com.api.user.data.controller;

import br.com.commons.web.controller.CrudController;
import br.com.domain.entity.Perfil;
import br.com.domain.entity.dto.PerfilDTO;
import br.com.domain.service.CrudService;
import br.com.domain.service.PerfilService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/perfil")
@Api("Serviços para gerenciar os perfis")
public class PerfilController implements CrudController<Perfil, PerfilDTO> {

    private final PerfilService perfilService;

    @Override
    public CrudService<Perfil, PerfilDTO> getCrudService() {
        return perfilService;
    }

}
