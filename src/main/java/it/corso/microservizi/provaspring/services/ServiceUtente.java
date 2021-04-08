package it.corso.microservizi.provaspring.services;

import it.corso.microservizi.provaspring.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ServiceUtente extends UserDetailsService {
    User saveUser(User user);
}
