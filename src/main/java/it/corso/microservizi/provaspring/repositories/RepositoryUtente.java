package it.corso.microservizi.provaspring.repositories;

import it.corso.microservizi.provaspring.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryUtente extends CrudRepository<User, String> {
}
