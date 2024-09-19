package com.cliente_persona.cliente_persona.service;


import com.cliente_persona.cliente_persona.data.ClientRepository;
import com.cliente_persona.cliente_persona.data.PersonRepository;
import com.cliente_persona.cliente_persona.model.pojo.Client;
import com.cliente_persona.cliente_persona.model.pojo.Person;
import com.cliente_persona.cliente_persona.model.request.CreateClientRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{


    private final ClientRepository clientRepository;

    private final PersonRepository personRepository;

    public ClientServiceImpl(ClientRepository clientRepository, PersonRepository personRepository) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Client> getClients(){
        List<Client> clientes = clientRepository.findAll();
        return clientes.isEmpty() ? null : clientes;
    }

    @Override
    public Client getClient(String productId) {

        return clientRepository.findById(Long.valueOf(productId)).orElse(null);
    }

    @Override
    public Boolean removeClient(String productId) {

        Client client = clientRepository.findById(Long.valueOf(productId)).orElse(null);

        if (client != null) {
            clientRepository.delete(client);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Client createClient(CreateClientRequest request) {

        if (request != null && StringUtils.hasLength(request.getContrasena().trim())
                && StringUtils.hasLength(request.getEstado().trim())
                && request.getPersonRequest() != null) {

            Optional<Person> optionalPerson = personRepository.findById(request.getPersonRequest().getIdentificacion());
            Person person;
            if (optionalPerson.isPresent()) {
                person = optionalPerson.get();
            } else {

                person = Person.builder()
                        .identificacion(request.getPersonRequest().getIdentificacion())
                        .nombre(request.getPersonRequest().getNombre())
                        .genero(request.getPersonRequest().getGenero())
                        .edad(request.getPersonRequest().getEdad())
                        .direccion(request.getPersonRequest().getDireccion())
                        .telefono(request.getPersonRequest().getTelefono())
                        .build();
                person=personRepository.save(person);
            }


            Client client = Client.builder().contrasena(request.getContrasena()).estado(request.getEstado())
                    .person(person).build();

            return clientRepository.save(client);
        } else {
            return null;
        }
    }

    @Override
    public Client updateClient(String clientId, CreateClientRequest clientRequest) {
        Client cliente = clientRepository.findById(Long.valueOf(clientId)).orElse(null);
        if (cliente !=null){
            cliente.setContrasena(clientRequest.getContrasena());
            cliente.setEstado(clientRequest.getEstado());
            cliente.getPerson().setIdentificacion(clientRequest.getPersonRequest().getIdentificacion());
            cliente.getPerson().setNombre(clientRequest.getPersonRequest().getNombre());
            cliente.getPerson().setEdad(clientRequest.getPersonRequest().getEdad());
            cliente.getPerson().setGenero(clientRequest.getPersonRequest().getGenero());
            cliente.getPerson().setDireccion(clientRequest.getPersonRequest().getDireccion());
            cliente.getPerson().setTelefono(clientRequest.getPersonRequest().getTelefono());

            clientRepository.save(cliente);
        }
        return cliente;
    }




}
