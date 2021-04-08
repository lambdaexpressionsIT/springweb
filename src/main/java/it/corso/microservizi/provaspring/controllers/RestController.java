package it.corso.microservizi.provaspring.controllers;

import it.corso.microservizi.provaspring.data.ClienteDTO;
import it.corso.microservizi.provaspring.data.ErrorDTO;
import it.corso.microservizi.provaspring.data.FormDTO;
import it.corso.microservizi.provaspring.data.RisultatoAggregatoDTO;
import it.corso.microservizi.provaspring.exceptions.MyNotFoundException;
import it.corso.microservizi.provaspring.services.ServiceCliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
@Slf4j
public class RestController {

    private final ServiceCliente serviceCliente;

    public RestController(ServiceCliente serviceCliente) {
        this.serviceCliente = serviceCliente;
    }

    @RequestMapping("test/{var}")
    public ResponseEntity<FormDTO> test(@PathVariable("var") String var) {
        FormDTO form = new FormDTO("TEST", "API");
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @RequestMapping("clienti")
    public ResponseEntity<List<ClienteDTO>> clienti() {
        List<ClienteDTO> clienti = serviceCliente.tuttiIClienti();
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }

    @RequestMapping("/clientiPerCitta/{citta}")
    public ResponseEntity<List<ClienteDTO>> clienti(@PathVariable String citta) {
        List<ClienteDTO> clienti = serviceCliente.cercaClientePerCitta(citta);
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }

    @RequestMapping("/sommaOrdinePerCliente")
    public ResponseEntity<?> sommaOrdinePerCliente() {
        List<RisultatoAggregatoDTO> clienti = new ArrayList<>(serviceCliente.sommaOrdinePerCliente());
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }

    @RequestMapping("/clientiPerStessaCittaFornitore/{citta}")
    public ResponseEntity<List<ClienteDTO>> clientiCittaFornitore(@PathVariable String citta) {
        List<ClienteDTO> clienti = serviceCliente.cercaClientePerStessaCittaFornitore(citta);
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }

    @RequestMapping(value = "/salvaCliente", method = RequestMethod.POST)
    public ResponseEntity<ClienteDTO> salvaCliente(@RequestBody ClienteDTO cliente) {
        ClienteDTO salvato = serviceCliente.salvaCliente(cliente);
        return new ResponseEntity<>(salvato, HttpStatus.OK);
    }
    @ExceptionHandler({MyNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleNotFound() {
        return new ResponseEntity<>(new ErrorDTO("Item not found",
                new Date()), HttpStatus.NOT_FOUND);
    }
}
