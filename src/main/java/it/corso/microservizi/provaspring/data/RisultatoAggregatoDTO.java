package it.corso.microservizi.provaspring.data;

import it.corso.microservizi.provaspring.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RisultatoAggregatoDTO {
    private Cliente cliente;
    private BigDecimal sommaOrdine;
}
