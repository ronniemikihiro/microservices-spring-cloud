package br.com.domain.service;

import br.com.domain.entity.Perfil;
import br.com.domain.entity.dto.PerfilDTO;
import br.com.domain.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilDTO salvar(PerfilDTO perfilDTO) {
        final Perfil perfil = perfilRepository.save(perfilDTO.toEntidade());
        return perfilRepository.obterPorId(perfil.getId());
    }
}
