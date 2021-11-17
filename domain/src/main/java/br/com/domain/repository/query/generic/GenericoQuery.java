package br.com.domain.repository.query.generic;

import br.com.domain.exception.errors.ErrorException;

import java.util.List;

public interface GenericoQuery<D> {
    D obterPorId(Long id) throws ErrorException;
    List<D> listarTodos() throws ErrorException;
}
