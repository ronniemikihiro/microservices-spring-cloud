package br.com.domain.service;

import br.com.domain.entity.Usuario;
import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.exception.rules.RuleException;
import br.com.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService implements CrudService<Usuario, UsuarioDTO> {

    private final UsuarioRepository usuarioRepository;

    @Override
    public JpaRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }

    @Override
    public void validarDados(UsuarioDTO dto) throws RuleException {

    }

    public Page<UsuarioDTO> searchByUsernamePaginated(String username, Pageable pageable) throws ErrorException {
        try {
            return usuarioRepository.obterPorUsernamePaginado(username, pageable);
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao consultar paginado o Usuário pelo username", e);
        }
    }

}
