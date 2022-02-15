package com.ceiba.pago.comando.fabrica;

import com.ceiba.pago.comando.ComandoCliente;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import org.springframework.stereotype.Component;

@Component
public class FabricaCliente {

    public Cliente crear(ComandoCliente comandoCliente) {
        return new Cliente(
                comandoCliente.getId(),
                comandoCliente.getNombre(),
                comandoCliente.getIdentificacion()
        );
    }
}