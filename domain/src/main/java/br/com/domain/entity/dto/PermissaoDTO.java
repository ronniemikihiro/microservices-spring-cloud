package br.com.domain.entity.dto;

import br.com.domain.entity.Permissao;
import lombok.*;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class PermissaoDTO extends DTO<Permissao> {

    private Long id;
    private String nome;
    private String codigo;
    private String action;

    @Override
    public Permissao toEntidade() {
        return Permissao.builder()
                .id(this.id)
                .nome(this.nome)
                .codigo(this.codigo)
                .action(this.action)
                .build();
    }

    @Override
    public RowMapper<PermissaoDTO> getRowMapper() {
        return (rs, i) ->
                PermissaoDTO.builder()
                        .id(rs.getLong(Permissao.Campo.ID))
                        .nome(rs.getString(Permissao.Campo.NOME))
                        .codigo(rs.getString(Permissao.Campo.CODIGO))
                        .action(rs.getString(Permissao.Campo.ACTION))
                        .build();
    }

}
