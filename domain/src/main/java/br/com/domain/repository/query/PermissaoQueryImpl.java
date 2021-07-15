package br.com.domain.repository.query;

import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.repository.query.generic.GenericoQueryImpl;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.StringUtils;

import java.util.List;

public class PermissaoQueryImpl extends GenericoQueryImpl<PermissaoDTO> implements PermissaoQuery {

    @Override
    public List<PermissaoDTO> listarPorPerfil(Long idPerfil) {
        final StringBuilder sql = new StringBuilder();
        sql.append(getConsulta("pm"));
        sql.append(" FROM PERMISSAO pm ");
        sql.append(" INNER JOIN PERFIL_PERMISSAO pp ON pp.ID_PERMISSAO = pm.ID ");
        sql.append(" INNER JOIN PERFIL p ON p.ID = pp.ID_PERFIL ");
        sql.append(" WHERE p.id = :idPerfil ");
        sql.append(" ORDER BY pm.codigo ");

        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idPerfil", idPerfil);

        return namedParameterJdbcTemplate.query(sql.toString(), params, getDTO().getRowMapper());
    }

//    @Override
//    public Page<PermissaoDTO> obterPorNomePaginado(PropertiesDatatable propertiesDatatable) {
//        final MapSqlParameterSource params = new MapSqlParameterSource();
//        final StringBuilder sql = new StringBuilder();
//
//        sql.append(getConsulta("p"));
//        sql.append(" FROM PERMISSAO p ");
//
//        if(!StringUtils.isEmpty(propertiesDatatable.getFilterField()) && !StringUtils.isEmpty(propertiesDatatable.getFilterValue())) {
//            sql.append(" WHERE LOWER(p." + propertiesDatatable.getFilterField() + ") LIKE :value ");
//            params.addValue("value", "%" + propertiesDatatable.getFilterValue().trim().toLowerCase() + "%");
//        }
//
//        return obterPaginado(sql.toString(), "p", params, getDTO().getRowMapper(), propertiesDatatable);
//    }
}
