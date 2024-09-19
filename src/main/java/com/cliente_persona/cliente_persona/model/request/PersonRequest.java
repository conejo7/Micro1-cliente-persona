package com.cliente_persona.cliente_persona.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

    private String identificacion;

    private String nombre;

    private String genero;

    private int edad;

    private String direccion;

    private String telefono;
}
