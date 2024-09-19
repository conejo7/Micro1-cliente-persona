package com.cliente_persona.cliente_persona.controller;

import com.cliente_persona.cliente_persona.model.pojo.Client;
import com.cliente_persona.cliente_persona.model.request.CreateClientRequest;
import com.cliente_persona.cliente_persona.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientsController {

    private final ClientService clientService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Client>> getClients(@RequestHeader Map<String, String> headers) {
        log.info("headers: {}", headers);
        List<Client> clients = clientService.getClients();
        if (clients != null) {
            return ResponseEntity.ok(clients);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/clientes/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable String clientId) {

        log.info("Request received for product {}", clientId);
        Client client = clientService.getClient(clientId);

        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/clientes/{clientesId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientesId) {

        Boolean removed = clientService.removeClient(clientesId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/clientes")
    public ResponseEntity<Client> createClient(@RequestBody CreateClientRequest request) {

        Client createdClient = clientService.createClient(request);

        if (createdClient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/clientes/{clientesId}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientesId, @RequestBody CreateClientRequest clientRequest) {
        Client client = clientService.updateClient(clientesId,clientRequest);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
