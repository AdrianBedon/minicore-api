package com.arbc.development.mvc.controller;

import com.arbc.development.mvc.model.Cliente;
import com.arbc.development.mvc.model.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class ClienteController {

    private final Logger log = LoggerFactory.getLogger(ClienteController.class);
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository)
    {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/clients")
    public ResponseEntity<Collection<Cliente>> clientes() {
        return ResponseEntity.ok().body(clienteRepository.findAll());
    }

    @GetMapping("/client/{id}")
    ResponseEntity<?> getCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(response -> ResponseEntity.ok().body(response))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));    
    }
    
    @PostMapping("/client")
    ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) throws URISyntaxException {
        log.info("Request to create client: {}", cliente);
        Cliente result = clienteRepository.save(cliente);
        return ResponseEntity.created(new URI("/api/client/" + result.getId()))
            .body(result);
    }

    @PutMapping("/client/{id}")
    ResponseEntity<Cliente> updateCliente(@Valid @RequestBody Cliente cliente) {
        log.info("Request to update client: {}", cliente);
        Cliente result = clienteRepository.save(cliente);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        log.info("Request to delete client: {}", id);
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
