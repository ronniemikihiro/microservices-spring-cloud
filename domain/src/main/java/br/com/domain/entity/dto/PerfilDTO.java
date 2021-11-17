package br.com.domain.entity.dto;

import br.com.domain.entity.Perfil;
import lombok.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class PerfilDTO extends DTO<Perfil> {

    private Long id;
    private String nome;
    private List<UsuarioDTO> usuarios;
    private List<PermissaoDTO> permissoes;

    @Override
    public Perfil toEntidade() {
        return Perfil.builder()
                .id(this.id)
                .nome(this.nome)
                .usuarios(CollectionUtils.isEmpty(this.usuarios) ? null : this.usuarios.stream().map(UsuarioDTO::toEntidade).collect(Collectors.toSet()))
                .permissoes(CollectionUtils.isEmpty(this.permissoes) ? null : this.permissoes.stream().map(PermissaoDTO::toEntidade).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public RowMapper<PerfilDTO> getRowMapper() {
        return (rs, i) ->
                PerfilDTO.builder()
                        .id(rs.getLong(Perfil.Campo.ID))
                        .nome(rs.getString(Perfil.Campo.NOME))
                        .build();
    }

}
