package it.corso.microservizi.provaspring.services;

import it.corso.microservizi.provaspring.data.ClienteDTO;
import it.corso.microservizi.provaspring.entities.Cliente;
import it.corso.microservizi.provaspring.exceptions.MyNotFoundException;
import it.corso.microservizi.provaspring.repositories.RepositoryCliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceClienteImpl implements ServiceCliente {

    private final RepositoryCliente repositoryCliente;

    public ServiceClienteImpl(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }
    @Override
    public ClienteDTO cercaClientePerID(Integer id) {
        return null;
    }

    @Override
    public ClienteDTO salvaCliente(ClienteDTO cliente) {

        if (cliente != null) {
            Cliente c = new Cliente();
            c.setNome(cliente.getNome());
            c.setCitta(cliente.getCitta());
            c.setValutazione(cliente.getValutazione());
            Cliente salvato = repositoryCliente.save(c);
            return convertiCliente(salvato);
        }
        return null;
    }

    @Override
    public List<ClienteDTO> tuttiIClienti() {
        Iterable<Cliente> clienti = repositoryCliente.findAll();
        return convertiClienti(clienti);
    }

    @Override
    public List<ClienteDTO> cercaClientePerCitta(String citta) {
        Iterable<Cliente> clienti = repositoryCliente.findAllByCitta(citta);
        return convertiClienti(clienti);

    }

    @Override
    public List<ClienteDTO> cercaClientePerStessaCittaFornitore(String citta) {
        Iterable<Cliente> clienti = repositoryCliente.findClienteFornitoreStessaCitta(citta);
        return convertiClienti(clienti);
    }

    private List<ClienteDTO> convertiClienti(Iterable<Cliente> clienti) {
        List<ClienteDTO> clientiDTO = new ArrayList<>();

        for (Cliente c: clienti) {
            ClienteDTO cliente = convertiCliente(c);
            clientiDTO.add(cliente);
        }

        if (clientiDTO.size() == 0) {
            throw new MyNotFoundException();
        }

        return clientiDTO;
    }

    private ClienteDTO convertiCliente(Cliente c) {
        ClienteDTO cliente = new ClienteDTO();
        cliente.setCitta(c.getCitta());
        cliente.setId(c.getId());
        cliente.setNome(c.getNome());
        cliente.setValutazione(c.getValutazione());
        return cliente;
    }
}
