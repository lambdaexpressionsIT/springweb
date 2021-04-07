package it.corso.microservizi.provaspring.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Fornitore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String citta;
    private BigDecimal commissione;

    @OneToMany(mappedBy = "fornitore")
    private Set<Cliente> clienti;

    @OneToMany(mappedBy = "fornitore")
    private Set<Ordine> ordini;
}
