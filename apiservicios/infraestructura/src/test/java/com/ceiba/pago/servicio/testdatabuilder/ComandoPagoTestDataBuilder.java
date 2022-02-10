package com.ceiba.pago.servicio.testdatabuilder;

import com.ceiba.pago.comando.ComandoPago;

import java.time.LocalDateTime;

import static com.ceiba.pago.servicio.testdatabuilder.CalcularFechaVencimiento.calcularFecha;

public class ComandoPagoTestDataBuilder {
    private Long id;
    private String cedulaUsuario;
    private String nombre;
    private String referenciaPago;
    private String aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaVencimiento;;

    public ComandoPagoTestDataBuilder() {
        this.id=2L;
        this.cedulaUsuario="1111111111";
        //this.nombre = UUID.randomUUID().toString().;
        this.nombre="xxxx";
        this.referenciaPago="1111";
        this.aplicaDescuento="S";
        this.valorBase=200000;
        this.valorTotal=200000;
        this.fechaRegistro = LocalDateTime.now();
        this.fechaVencimiento=calcularFecha(20);
    }

    public ComandoPagoTestDataBuilder conCedula(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
        return this;
    }

    public ComandoPagoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    public ComandoPagoTestDataBuilder conReferenciaPago(String referenciaPago) {
        this.referenciaPago = referenciaPago;
        return this;
    }
    public ComandoPagoTestDataBuilder conAplicaDescuento(String aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
        return this;
    }

    public ComandoPagoTestDataBuilder conAplicaConValorBase(double valorBase) {
        this.valorBase = valorBase;
        return this;
    }

    public ComandoPagoTestDataBuilder conAplicaConValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public ComandoPagoTestDataBuilder conAplicaConFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public ComandoPagoTestDataBuilder conAplicaConFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
        return this;
    }

    public ComandoPago build() {
        return new ComandoPago(id,cedulaUsuario,nombre,referenciaPago,aplicaDescuento,valorBase,valorTotal,fechaRegistro,fechaVencimiento);
    }

}
