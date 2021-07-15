package br.com.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "EMPRESA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Empresa implements Entidade {

    @Id
    @Column(name = Campo.ID, nullable = false)
//    @GenericGenerator(name = "SEQ_EMPRESA_GENERATOR",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {@Parameter(name = "sequence_name", value = "SEQ_EMPRESA_ID")}
//    )
//    @GeneratedValue(generator = "SEQ_EMPRESA_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Campo.NOME, nullable = false)
    private String nome;

    @Column(name = Campo.CONTA, nullable = false)
    private String conta;

    @Column(name = Campo.USERNAME, nullable = false)
    private String username;

    @Column(name = Campo.SENHA, nullable = false)
    private String password;

    @Column(name = Campo.CAIXA, nullable = false)
    private String caixa;

    @Override
    public AbstractCampo field() {
        return new Campo();
    }

    public static class Campo extends AbstractCampo {
        public static final String ID = "ID";
        public static final String NOME = "NOME";
        public static final String CONTA = "CONTA";
        public static final String USERNAME = "USERNAME";
        public static final String SENHA = "SENHA";
        public static final String CAIXA = "CAIXA";
    }

}
