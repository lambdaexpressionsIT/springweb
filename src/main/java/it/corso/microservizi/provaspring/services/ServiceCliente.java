package it.corso.microservizi.provaspring.services;

import ch.qos.logback.core.net.server.Client;
import it.corso.microservizi.provaspring.data.ClienteDTO;

import java.util.List;

public interface ServiceCliente {
    ClienteDTO cercaClientePerID(Integer id);
    ClienteDTO salvaCliente(ClienteDTO cliente);
    List<ClienteDTO> tuttiIClienti();
    List<ClienteDTO> cercaClientePerCitta(String citta);
    List<ClienteDTO> cercaClientePerStessaCittaFornitore(String citta);
}
