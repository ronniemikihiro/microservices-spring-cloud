package br.com.domain.service;

import br.com.domain.entity.Empresa;
import br.com.domain.entity.dto.EmpresaDTO;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.exception.rules.RuleException;
import br.com.domain.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaService implements CrudService<Empresa, EmpresaDTO> {

    private final EmpresaRepository empresaRepository;

    @Override
    public JpaRepository<Empresa, Long> getRepository() {
        return empresaRepository;
    }

    @Override
    public void validarDados(EmpresaDTO dto) throws RuleException {

    }
}
