package com.ceiba.pago.comando;

import com.ceiba.pago.modelo.entidad.cliente.Identificacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCliente {
    private Long id;
    private String nombre;
    private Identificacion identificacion;

}
