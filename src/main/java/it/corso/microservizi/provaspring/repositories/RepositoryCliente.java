package it.corso.microservizi.provaspring.repositories;

import it.corso.microservizi.provaspring.entities.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryCliente extends CrudRepository<Cliente, Integer> {

    @Query("select c from Cliente c where c.citta=?1")
    Iterable<Cliente> findAllByCitta(String citta);

    @Query("select c from Cliente c where c.citta=?1 and c.fornitore.citta=?1")
    Iterable<Cliente> findClienteFornitoreStessaCitta(String citta);

}
