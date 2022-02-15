package com.ceiba.pago.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.pago.comando.ComandoCliente;
import com.ceiba.pago.comando.fabrica.FabricaCliente;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.servicio.cliente.ServicioCrearCliente;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearCliente implements ManejadorComandoRespuesta<ComandoCliente, ComandoRespuesta<Long>> {

    private final FabricaCliente fabricaCliente;
    private final ServicioCrearCliente servicioCrearCliente;

    public ManejadorCrearCliente(FabricaCliente fabricaCliente, ServicioCrearCliente servicioCrearCliente) {
        this.fabricaCliente = fabricaCliente;
        this.servicioCrearCliente = servicioCrearCliente;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoCliente comandoCliente) {
        Cliente cliente = this.fabricaCliente.crear(comandoCliente);
        return new ComandoRespuesta<>(this.servicioCrearCliente.ejecutar(cliente));
    }
}
