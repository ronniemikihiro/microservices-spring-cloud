package br.com.domain.repository;

import br.com.domain.entity.Usuario;
import br.com.domain.repository.query.UsuarioQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioQuery {

}
