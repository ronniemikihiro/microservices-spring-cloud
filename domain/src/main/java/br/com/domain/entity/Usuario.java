package br.com.domain.entity;

import br.com.domain.enums.EnumTipoAutenticacao;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Usuario implements Entidade {

    @Id
    @Column(name = Campo.ID, nullable = false)
//    @GenericGenerator(name = "SEQ_USUARIO_GENERATOR",
//                      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//                      parameters = { @Parameter(name = "sequence_name", value = "SEQ_USUARIO_ID") }
//    )
//    @GeneratedValue(generator = "SEQ_USUARIO_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Campo.NOME, nullable = false)
    private String nome;

    @Column(name = Campo.USERNAME, nullable = false)
    private String username;

    @Column(name = Campo.SENHA, insertable = true, updatable = false)
    private String senha;

    @Column(name = Campo.SETOR)
    private String setor;

    @Column(name = Campo.CARGO)
    private String cargo;

    @Column(name = Campo.DOMINIO)
    private String dominio;

    @Column(name = Campo.TIPO_AUTENTICACAO, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumTipoAutenticacao tipoAutenticacao;

    @JoinColumn(name = Campo.ID_EMPRESA, referencedColumnName = Empresa.Campo.ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa empresa;

    @JoinColumn(name = Campo.ID_PERFIL, referencedColumnName = Perfil.Campo.ID)
    @ManyToOne(fetch = FetchType.LAZY)
    private Perfil perfil;

    @Override
    public AbstractCampo field() {
        return new Campo();
    }

    public static class Campo extends AbstractCampo {
        public static final String ID = "ID";
        public static final String NOME = "NOME";
        public static final String USERNAME = "USERNAME";
        public static final String SENHA = "SENHA";
        public static final String SETOR = "SETOR";
        public static final String CARGO = "CARGO";
        public static final String DOMINIO = "DOMINIO";
        public static final String TIPO_AUTENTICACAO = "TIPO_AUTENTICACAO";
        public static final String ID_EMPRESA = "ID_EMPRESA";
        public static final String ID_PERFIL = "ID_PERFIL";
    }
}