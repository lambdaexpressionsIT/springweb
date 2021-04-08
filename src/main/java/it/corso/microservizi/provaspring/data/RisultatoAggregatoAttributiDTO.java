package it.corso.microservizi.provaspring.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RisultatoAggregatoAttributiDTO {
    private String nomeCliente;
    private String cittaCliente;
    private String nomeFornitore;
    private BigDecimal commissione;
}
