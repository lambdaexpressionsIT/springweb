package it.corso.microservizi.provaspring.services;

import it.corso.microservizi.provaspring.data.ClienteDTO;
import it.corso.microservizi.provaspring.data.RisultatoAggregatoDTO;
import it.corso.microservizi.provaspring.data.RisultatoAggregatoAttributiDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceCliente {
    ClienteDTO cercaClientePerID(Integer id);
    ClienteDTO salvaCliente(ClienteDTO cliente);
    List<ClienteDTO> tuttiIClienti();
    List<ClienteDTO> cercaClientePerCitta(String citta);
    List<ClienteDTO> cercaClientePerStessaCittaFornitore(String citta);
    List<RisultatoAggregatoDTO> sommaOrdinePerCliente();
    List<RisultatoAggregatoDTO> sommaOrdineMinimoPerCliente(BigDecimal minOrdine);
    List<RisultatoAggregatoAttributiDTO> datiSingoli();
}
