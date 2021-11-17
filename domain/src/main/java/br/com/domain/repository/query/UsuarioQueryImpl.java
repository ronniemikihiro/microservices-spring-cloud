package br.com.domain.repository.query;

import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.repository.query.generic.GenericoQueryImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class UsuarioQueryImpl extends GenericoQueryImpl<UsuarioDTO> implements UsuarioQuery {

    @Override
    public UsuarioDTO obterPorUsername(String username) {
        final StringBuilder sql = new StringBuilder();

        sql.append(getConsulta("u"));
        sql.append(" FROM USUARIO u ");
        sql.append(" WHERE u.USERNAME = :username ");

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", username.trim());

        try {
            return (UsuarioDTO) namedParameterJdbcTemplate.queryForObject(sql.toString(), params, getRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Page<UsuarioDTO> obterPorUsernamePaginado(String username, Pageable pageable) {
        final StringBuilder sql = new StringBuilder();
        sql.append(" FROM USUARIO u ");
        sql.append(" WHERE LOWER(u.USERNAME) like :username ");

        final StringBuilder sqlCount = new StringBuilder();
        sqlCount.append(" SELECT COUNT(u.id) ");
        sqlCount.append(sql.toString());

        final StringBuilder sqlSearch = new StringBuilder();
        sqlSearch.append(getConsulta("u"));
        sqlSearch.append(sql.toString());

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", "%" + username.trim().toLowerCase() + "%");

        final Long count = namedParameterJdbcTemplate.queryForObject(sqlCount.toString(), params, Long.class);

        return new PageImpl<>(namedParameterJdbcTemplate.query(sqlSearch.toString(), params, getRowMapper()), pageable, count);
    }

}
