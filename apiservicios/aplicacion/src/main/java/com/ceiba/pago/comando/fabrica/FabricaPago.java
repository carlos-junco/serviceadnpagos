package com.ceiba.pago.comando.fabrica;

import com.ceiba.pago.modelo.entidad.Pago;
import org.springframework.stereotype.Component;

import com.ceiba.pago.comando.ComandoPago;

import java.time.LocalDateTime;

@Component
public class FabricaPago {

    public Pago crear(ComandoPago comandoPago) {
        return new Pago(
                comandoPago.getId(),
                comandoPago.getCedulaUsuario(),
                comandoPago.getNombre(),
                comandoPago.getReferenciaPago(),
                comandoPago.getAplicaDescuento(),
                comandoPago.getValorBase(),
                comandoPago.getValorTotal(),
                comandoPago.getFechaRegistro(),
                comandoPago.getFechaVencimiento()
                );
    }
}