package br.com.domain.repository;

import br.com.domain.entity.Permissao;
import br.com.domain.repository.query.PermissaoQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoQuery {

}
