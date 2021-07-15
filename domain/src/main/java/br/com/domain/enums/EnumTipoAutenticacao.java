package br.com.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public enum EnumTipoAutenticacao {
    LDAP("LDAP", "Apenas LDAP"),
    SO("SO", "Apenas Sistema operacional");

    private String codigo;

    @ToString.Include
    private String descricao;

    public static EnumTipoAutenticacao getEnum(String codigo) {
        return codigo == null ? null : Arrays.stream(EnumTipoAutenticacao.values())
                .filter(a -> a.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
