package br.com.domain.repository.query.generic;

import java.util.List;

public interface GenericoQuery<D> {
    D obterPorId(Long id);
    List<D> listarTodos();
}
