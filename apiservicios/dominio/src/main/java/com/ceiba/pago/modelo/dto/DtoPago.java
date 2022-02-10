package com.ceiba.pago.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class DtoPago {
    private Long id;
    private String cedulaUsuario;
    private String nombre;
    private String referenciaPago;
    private String aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaVencimiento;
}
