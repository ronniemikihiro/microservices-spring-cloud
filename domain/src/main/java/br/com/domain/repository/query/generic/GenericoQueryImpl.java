package br.com.domain.repository.query.generic;

import br.com.domain.entity.Entidade;
import br.com.domain.entity.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class GenericoQueryImpl<D> implements GenericoQuery<D> {

    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Class<D> classDto;
    private DTO dto;
    private Entidade entidade;

    protected GenericoQueryImpl() {
        try {
            this.classDto = (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            this.dto = (DTO) this.classDto.getConstructor().newInstance();
            this.entidade = this.dto.getEntidade();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTabela() {
        return this.entidade.getClass().getAnnotation(Table.class).name();
    }

    protected DTO getDTO() {
        return this.dto;
    }

    protected String getConsulta(String alias) {
        final StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT ");
        this.entidade.field().values().forEach(f -> sqlBuilder.append(alias).append(".").append(f).append(" AS ").append(f).append(","));
        final String sql = sqlBuilder.toString();
        return sql.substring(0, sql.length() - 1).concat(" ");
    }

    @Override
    public D obterPorId(Long id) {
        try {
            final StringBuilder sql = new StringBuilder();
            sql.append(getConsulta("e"));
            sql.append(" FROM ").append(getTabela()).append(" e ");
            sql.append(" WHERE e.ID = :id ");

            final MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);

            return (D) namedParameterJdbcTemplate.queryForObject(sql.toString(), params, this.dto.getRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<D> listarTodos() {
        final StringBuilder sql = new StringBuilder();
        sql.append(getConsulta("e"));
        sql.append(" FROM ").append(getTabela()).append(" e ");

        return namedParameterJdbcTemplate.query(sql.toString(), this.dto.getRowMapper());
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

