package br.com.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERFIL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Perfil implements Entidade {

    @Id
    @Column(name = Campo.ID, nullable = false)
//    @GenericGenerator(name = "SEQ_PERFIL_GENERATOR",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = { @Parameter(name = "sequence_name", value = "SEQ_PERFIL_ID") }
//    )
//    @GeneratedValue(generator = "SEQ_PERFIL_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Campo.NOME, nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="PERFIL_PERMISSAO",
            joinColumns={ @JoinColumn(name = "ID_PERFIL", referencedColumnName = Perfil.Campo.ID) },
            inverseJoinColumns={ @JoinColumn(name = "ID_PERMISSAO", referencedColumnName = Permissao.Campo.ID) })
    private Set<Permissao> permissoes;

    @OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios;

    @Override
    public AbstractCampo field() {
        return new Campo();
    }

    public static class Campo extends AbstractCampo {
        public static final String ID = "ID";
        public static final String NOME = "NOME";
    }

}
