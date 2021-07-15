package br.com.domain.service;

import br.com.domain.entity.Empresa;
import br.com.domain.entity.dto.EmpresaDTO;
import br.com.domain.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaDTO salvar(EmpresaDTO empresaDTO) {
        final Empresa empresa = empresaRepository.save(empresaDTO.toEntidade());
        return empresaRepository.obterPorId(empresa.getId());
    }
}
