package it.corso.microservizi.provaspring.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ChiaveOrdine implements Serializable {

    @Column(name = "id_ordine")
    private Integer idOrdine;

    @Column(name = "id_fornitore")
    private Integer idFornitore;

    @Column(name = "id_cliente")
    private Integer idCliente;

}
