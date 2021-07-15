package br.com.domain.repository;

import br.com.domain.entity.Perfil;
import br.com.domain.repository.query.PerfilQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long>, PerfilQuery {

}
