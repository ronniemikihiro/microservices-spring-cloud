package br.com.domain.service;

import br.com.domain.entity.dto.DTO;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.exception.rules.RuleException;
import br.com.domain.repository.query.generic.GenericoQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("unchecked")
public interface CrudService<T, D> {

    Logger log = LoggerFactory.getLogger(CrudService.class);

    JpaRepository<T, Long> getRepository();
    void validarDados(D dto) throws RuleException;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    default T salvar(D dto) throws RuleException, ErrorException {
        try {
            validarDados(dto);
            return getRepository().save(((DTO<T>) dto).toEntidade());
        } catch (RuleException e) {
            throw e;
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao salvar a entidade " + ((DTO<T>) dto).getEntidade().getClass(), e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    default void excluirPorId(Long id) throws ErrorException {
        try {
            if (getRepository().existsById(id)) {
                getRepository().deleteById(id);
            }
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao deletar a entidade", e);
        }
    }

    default List<D> listarTodos() throws ErrorException {
        try {
            return ((GenericoQuery<D>) getRepository()).listarTodos();
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao listar todos.", e);
        }
    }

    default D obterPorId(Long id) throws ErrorException {
        try {
            return ((GenericoQuery<D>) getRepository()).obterPorId(id);
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao obter por id", e);
        }
    }

}
