package br.com.domain.service;

import br.com.domain.entity.Perfil;
import br.com.domain.entity.dto.PerfilDTO;
import br.com.domain.exception.rules.CampoObrigatorioException;
import br.com.domain.exception.rules.RuleException;
import br.com.domain.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PerfilService implements CrudService<Perfil, PerfilDTO> {

    private final PerfilRepository perfilRepository;

    @Override
    public JpaRepository<Perfil, Long> getRepository() {
        return perfilRepository;
    }

    @Override
    public void validarDados(PerfilDTO perfilDTO) throws RuleException {
        if (StringUtils.isBlank(perfilDTO.getNome())) {
            throw new CampoObrigatorioException("Nome");
        }
    }
}
