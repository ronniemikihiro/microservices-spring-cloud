package br.com.domain.service;

import br.com.domain.entity.Permissao;
import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.exception.rules.RuleException;
import br.com.domain.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PermissaoService implements CrudService<Permissao, PermissaoDTO> {

    private final PermissaoRepository permissaoRepository;

    @Override
    public JpaRepository<Permissao, Long> getRepository() {
        return permissaoRepository;
    }

    @Override
    public void validarDados(PermissaoDTO dto) throws RuleException {

    }

    public List<PermissaoDTO> listarPorPerfil(Long idPerfil) throws ErrorException {
        return permissaoRepository.listarPorPerfil(idPerfil);
    }

//    public Page<PermissaoDTO> searchByNomePaginated(PropertiesDatatable propertiesDatatable) {
//        return permissaoRepository.obterPorNomePaginado(propertiesDatatable);
//    }

}
