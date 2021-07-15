package br.com.domain.service;

import br.com.domain.entity.Permissao;
import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;

//    public Page<PermissaoDTO> searchByNomePaginated(PropertiesDatatable propertiesDatatable) {
//        return permissaoRepository.obterPorNomePaginado(propertiesDatatable);
//    }

	public PermissaoDTO salvar(PermissaoDTO permissaoDTO) {
		final Permissao permissao = permissaoRepository.save(permissaoDTO.toEntidade());
		return permissaoRepository.obterPorId(permissao.getId());
	}

    public List<PermissaoDTO> listarPorPerfil(Long idPerfil) {
        return permissaoRepository.listarPorPerfil(idPerfil);
    }

}
