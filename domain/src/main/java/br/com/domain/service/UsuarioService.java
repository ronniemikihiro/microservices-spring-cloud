package br.com.domain.service;

import br.com.domain.entity.Usuario;
import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Page<UsuarioDTO> searchByUsernamePaginated(String username, Pageable pageable) {
		return usuarioRepository.obterPorUsernamePaginado(username, pageable);
	}

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.save(usuarioDTO.toEntidade());
        return usuarioRepository.obterPorId(usuario.getId());
    }

}
