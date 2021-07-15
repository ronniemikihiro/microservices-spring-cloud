package br.com.domain.repository.query;

import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.repository.query.generic.GenericoQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioQuery extends GenericoQuery<UsuarioDTO> {

    UsuarioDTO obterPorUsername(String username);
    Page<UsuarioDTO> obterPorUsernamePaginado(String username, Pageable pageable);
}
