package br.com.domain.repository.query;

import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.repository.query.generic.GenericoQueryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

@Slf4j
public class PermissaoQueryImpl extends GenericoQueryImpl<PermissaoDTO> implements PermissaoQuery {

    @Override
    public List<PermissaoDTO> listarPorPerfil(Long idPerfil) throws ErrorException {
        try {
            final String sql = getConsulta("pm") +
                    """
                    FROM PERMISSAO pm
                    INNER JOIN PERFIL_PERMISSAO pp ON pp.ID_PERMISSAO = pm.ID
                    INNER JOIN PERFIL p ON p.ID = pp.ID_PERFIL
                    WHERE p.id = :idPerfil
                    ORDER BY pm.codigo
                    """;

            final MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("idPerfil", idPerfil);

            return namedParameterJdbcTemplate.query(sql, params, getRowMapper());
        } catch (Exception e) {
            throw new ErrorException(log, "Erro ao listar as Permissões por Perfil", e);
        }
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
