package br.com.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "PERMISSAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Permissao implements Entidade {

    @Id
    @Column(name = Campo.ID, nullable = false)
//    @GenericGenerator(name = "SEQ_PERMISSAO_GENERATOR",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = { @Parameter(name = "sequence_name", value = "SEQ_PERMISSAO_ID") }
//    )
//    @GeneratedValue(generator = "SEQ_PERMISSAO_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Campo.NOME, nullable = false)
    private String nome;

    @Column(name = Campo.CODIGO, nullable = false)
    private String codigo;

    @Column(name = Campo.ACTION)
    private String action;

    @Override
    public AbstractCampo field() {
        return new Campo();
    }

    public static class Campo extends AbstractCampo {
        public static final String ID = "ID";
        public static final String NOME = "NOME";
        public static final String CODIGO = "CODIGO";
        public static final String ACTION = "ACTION";
    }

}