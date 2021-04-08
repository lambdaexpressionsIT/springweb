package it.corso.microservizi.provaspring.repositories;

import it.corso.microservizi.provaspring.entities.Cliente;
import it.corso.microservizi.provaspring.data.RisultatoAggregatoDTO;
import it.corso.microservizi.provaspring.data.RisultatoAggregatoAttributiDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface RepositoryCliente extends CrudRepository<Cliente, Integer> {

    @Query("select c from Cliente c where c.citta=?1")
    Iterable<Cliente> findAllByCitta(String citta);

    @Query("select c from Cliente c where c.citta=?1 and c.fornitore.citta=?1")
    Iterable<Cliente> findClienteFornitoreStessaCitta(String citta);

    @Query("select new it.corso.microservizi.provaspring.data.RisultatoAggregatoDTO(o.cliente, sum(o.totaleOrdine)) from Ordine o group by o.cliente")
    Iterable<RisultatoAggregatoDTO> sommaOrdinePerCliente();

    @Query("select new it.corso.microservizi.provaspring.data.RisultatoAggregatoDTO(o.cliente, sum(o.totaleOrdine)) from Ordine o group by o.cliente having sum(o.totaleOrdine) > ?1")
    Iterable<RisultatoAggregatoDTO> sommaOrdineMinimoPerCliente(BigDecimal minOrdine);

    @Query("select new it.corso.microservizi.provaspring.data.RisultatoAggregatoAttributiDTO(c.nome, c.citta, c.fornitore.nome, c.fornitore.commissione) from Cliente c")
    Iterable<RisultatoAggregatoAttributiDTO> datiSingoli();

}
