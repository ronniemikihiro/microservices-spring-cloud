package br.com.domain.repository.query;

import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.repository.query.generic.GenericoQuery;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PermissaoQuery extends GenericoQuery<PermissaoDTO> {

    List<PermissaoDTO> listarPorPerfil(Long idPerfil) throws ErrorException;
//    Page<PermissaoDTO> obterPorNomePaginado(PropertiesDatatable propertiesDatatable);
}
