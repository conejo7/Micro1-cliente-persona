package com.cliente_persona.cliente_persona.service;

import com.cliente_persona.cliente_persona.model.pojo.Client;
import com.cliente_persona.cliente_persona.model.request.CreateClientRequest;

import java.util.List;

public interface ClientService {


    List<Client> getClients();

    Client getClient(String clientId);

    Boolean removeClient(String clientId);

    Client createClient(CreateClientRequest request);

    Client updateClient(String clientId, CreateClientRequest clientRequest);


}
