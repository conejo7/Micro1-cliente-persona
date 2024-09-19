package com.cliente_persona.cliente_persona.controller;

import com.cliente_persona.cliente_persona.model.pojo.Client;
import com.cliente_persona.cliente_persona.service.ClientService;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientsControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientsController clientController;




    @Test
     void test_returns_list_of_clients_when_available() {

        List<Client> clientes = Arrays.asList(new Client(), new Client());

        when(clientService.getClients()).thenReturn(clientes);

        ResponseEntity<List<Client>> response = clientController.getClients(Collections.emptyMap());

        assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(clientes, response.getBody());
    }

    @Test
     void testGetClients_ReturnsEmptyList() {

        when(clientService.getClients()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Client>> response = clientController.getClients(Collections.emptyMap());

        assertEquals(200, response.getStatusCode().value());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
     void testGetClients_ServiceReturnsNull() {

        when(clientService.getClients()).thenReturn(null);

        ResponseEntity<List<Client>> response = clientController.getClients(Collections.emptyMap());

        assertEquals(200, response.getStatusCode().value());
        assertEquals(Collections.emptyList(), response.getBody());
    }


}