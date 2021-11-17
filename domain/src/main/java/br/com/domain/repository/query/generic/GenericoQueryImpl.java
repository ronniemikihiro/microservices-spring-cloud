package br.com.domain.repository.query.generic;

import br.com.domain.entity.Entidade;
import br.com.domain.entity.dto.DTO;
import br.com.domain.exception.errors.ErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Slf4j
@SuppressWarnings("unchecked")
public abstract class GenericoQueryImpl<D> implements GenericoQuery<D> {

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<D> rowMapper;
    private Entidade entidade;

    protected GenericoQueryImpl() {
        try {
            final Class<D> classDto = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            final Object dto = classDto.getConstructor().newInstance();
            this.entidade = ((DTO) dto).getEntidade();
            this.rowMapper = ((DTO) dto).getRowMapper();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTabela() {
        return this.entidade.getClass().getAnnotation(Table.class).name();
    }

    protected RowMapper<D> getRowMapper() {
        return this.rowMapper;
    }

    protected String getConsulta(String alias) {
        final StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT ");
        this.entidade.field().values().forEach(f -> sqlBuilder.append(alias).append(".").append(f).append(" AS ").append(f).append(","));
        final String sql = sqlBuilder.toString();
        return sql.substring(0, sql.length() - 1).concat(" ");
    }

    @Override
    public D obterPorId(Long id) throws ErrorException {
        try {
            final String sql = getConsulta("e") + " FROM " + getTabela() + " e WHERE e.ID = :id ";
            final MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);
            return namedParameterJdbcTemplate.queryForObject(sql, params, getRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao obter por id", e);
        }
    }

    @Override
    public List<D> listarTodos() throws ErrorException {
        try {
            final String sql = getConsulta("e") + " FROM " + getTabela() + " e ";
            return namedParameterJdbcTemplate.query(sql, getRowMapper());
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao listar todos", e);
        }
    }

//    protected Page<D> obterPaginado(String sql, String alias, MapSqlParameterSource params, RowMapper rowMapper, PropertiesDatatable propertiesDatatable) {
//        final StringBuilder sqlCount = new StringBuilder();
//        sqlCount.append(" SELECT COUNT(" + alias + ".id) ");
//        sqlCount.append(sql.substring(sql.indexOf("FROM")));
//
//        final Long count = namedParameterJdbcTemplate.queryForObject(sqlCount.toString(), params, Long.class);
//
//        final StringBuilder sqlQuery = new StringBuilder();
//        sqlQuery.append(" SELECT * FROM ( ");
//        sqlQuery.append("         SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( ");
//        sqlQuery.append(              sql);
//        sqlQuery.append(propertiesDatatable.getOrder(alias));
//        sqlQuery.append("         ) ROW_ WHERE ROWNUM <= :rownum_fim ");
//        sqlQuery.append(" ) WHERE ROWNUM_ > :rownum_inicio ");
//
//        final Pageable pageable = propertiesDatatable.getPageable();
//        final int rownumFim = pageable.getPageSize() * (pageable.getPageNumber() + 1);
//
//        params.addValue("rownum_inicio", rownumFim - pageable.getPageSize());
//        params.addValue("rownum_fim", rownumFim);
//
//        return new PageImpl<>(namedParameterJdbcTemplate.query(sqlQuery.toString(), params, rowMapper), pageable, count);
//    }

}

