package br.com.domain.repository;

import br.com.domain.entity.Empresa;
import br.com.domain.repository.query.EmpresaQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>, EmpresaQuery {

}
