package it.corso.microservizi.provaspring.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;
    private String citta;
    private Integer valutazione;

    @ManyToOne
    @JoinColumn(name="fornitore_id", nullable = true, foreignKey=@ForeignKey(name = "Fk_id_fornitore"))
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Fornitore fornitore;

    @OneToMany(mappedBy = "cliente")
    private Set<Ordine> ordini;
}
