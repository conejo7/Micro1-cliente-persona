package com.cliente_persona.cliente_persona.data;

import com.cliente_persona.cliente_persona.model.pojo.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client,Long> {


}
