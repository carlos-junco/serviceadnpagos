package com.ceiba.pago.modelo.dto;

import com.ceiba.pago.modelo.entidad.cliente.Identificacion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class DtoCliente {
    private Long id;
    private String nombre;
    private Identificacion identificacion;
}
