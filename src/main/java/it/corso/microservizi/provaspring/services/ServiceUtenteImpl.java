package it.corso.microservizi.provaspring.services;

import it.corso.microservizi.provaspring.entities.User;
import it.corso.microservizi.provaspring.repositories.RepositoryUtente;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceUtenteImpl implements ServiceUtente {

    private final RepositoryUtente repositoryUtente;

    public ServiceUtenteImpl(RepositoryUtente repositoryUtente) {
        this.repositoryUtente = repositoryUtente;
    }

    @Override
    public User saveUser(User user) {
        return repositoryUtente.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repositoryUtente.findById(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
