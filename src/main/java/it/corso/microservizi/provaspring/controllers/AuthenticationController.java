package it.corso.microservizi.provaspring.controllers;

import io.jsonwebtoken.*;
import it.corso.microservizi.provaspring.config.TokensUtil;
import it.corso.microservizi.provaspring.data.AuthResult;
import it.corso.microservizi.provaspring.data.JwtDTO;
import it.corso.microservizi.provaspring.data.UserRequestDTO;
import it.corso.microservizi.provaspring.entities.User;
import it.corso.microservizi.provaspring.services.ServiceUtente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final ServiceUtente serviceUtente;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokensUtil tokensUtil;

    public AuthenticationController(ServiceUtente serviceUtente, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokensUtil tokensUtil) {
        this.serviceUtente = serviceUtente;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokensUtil = tokensUtil;
    }


    //login post (username password)  -> jwtToken

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserRequestDTO authRequest) {
        //autenticazione

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        UserDetails userDetails = serviceUtente.loadUserByUsername(authRequest.getUsername());

        String jwtToken = tokensUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtDTO(jwtToken));
    }

    //register  post (username password)  -> jwtToken  (prima crea utente e poi fa login)

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserRequestDTO authRequest) {

        serviceUtente.saveUser(User.builder()
        .username(authRequest.getUsername())
        .password(passwordEncoder.encode(authRequest.getPassword()))
        .build());

        return login(authRequest);

    }

    //checkToken (jwtToken) -> true/false
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody JwtDTO jwt) {
        String token = jwt.getJwtToken();
        String username = tokensUtil.getUsernameFromToken(token);
        UserDetails userDetails = serviceUtente.loadUserByUsername(username);

        if (!tokensUtil.validateToken(token, userDetails)) {
            throw new JwtException("jwt not valid");
        }
        return ResponseEntity.ok(new AuthResult(true));
    }

    @ExceptionHandler({JwtException.class, ExpiredJwtException.class, UnsupportedJwtException.class, MalformedJwtException.class, SignatureException.class})
    public ResponseEntity<?> jwtException() {
        return ResponseEntity.badRequest().body(new AuthResult(false));
    }

}
