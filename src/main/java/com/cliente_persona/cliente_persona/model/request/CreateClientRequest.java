package com.cliente_persona.cliente_persona.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {

    private String contrasena;

    private String estado;

    private PersonRequest personRequest;

}
