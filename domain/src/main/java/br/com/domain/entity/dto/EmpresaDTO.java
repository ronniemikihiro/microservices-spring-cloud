package br.com.domain.entity.dto;

import br.com.domain.entity.Empresa;
import lombok.*;
import org.springframework.jdbc.core.RowMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class EmpresaDTO extends DTO<Empresa, EmpresaDTO> {

    private Long id;
    private String nome;
    private String conta;
    private String username;
    private String password;
    private String caixa;

    @Override
    public Empresa toEntidade() {
        return Empresa.builder()
                .id(this.id)
                .nome(this.nome)
                .conta(this.conta)
                .username(this.username)
                .password(this.password)
                .caixa(this.caixa)
                .build();
    }

    @Override
    public RowMapper<EmpresaDTO> getRowMapper() {
        return (rs, i) ->
                EmpresaDTO.builder()
                        .id(rs.getLong(Empresa.Campo.ID))
                        .nome(rs.getString(Empresa.Campo.NOME))
                        .conta(rs.getString(Empresa.Campo.CONTA))
                        .username(rs.getString(Empresa.Campo.USERNAME))
                        .password(rs.getString(Empresa.Campo.SENHA))
                        .caixa(rs.getString(Empresa.Campo.CAIXA))
                        .build();
    }

}
