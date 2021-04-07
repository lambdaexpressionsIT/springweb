package it.corso.microservizi.provaspring.entities;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
public class Ordine {

    @EmbeddedId
    private ChiaveOrdine id;

    @ManyToOne
    @MapsId("id_fornitore")
    private Fornitore fornitore;

    @ManyToOne
    @MapsId("id_cliente")
    private Cliente cliente;

    private BigDecimal totaleOrdine;

    private Date dataOrdine;
}
