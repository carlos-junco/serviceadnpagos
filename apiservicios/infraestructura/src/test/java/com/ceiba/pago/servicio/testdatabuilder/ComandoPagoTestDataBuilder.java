package com.ceiba.pago.servicio.testdatabuilder;

import com.ceiba.pago.comando.ComandoPago;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class ComandoPagoTestDataBuilder {
    public static final int DIAS_PROXIMO_PAGO = 20;
    public static final double PORCENTAJE_DESCUENTO = 0.15;
    private Long id;
    private String cedulaUsuario;
    private String nombre;
    private String referenciaPago;
    private boolean aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaProximoPago;;

    public ComandoPagoTestDataBuilder() {
        this.id=2L;
        this.cedulaUsuario="1111111111";
        this.nombre="xxxx";
        this.referenciaPago="1111";
        this.aplicaDescuento=true;
        this.valorBase=200000;
        //this.valorTotal=200000;
        this.fechaRegistro = LocalDateTime.now();
        generarFechaProximoPago(fechaRegistro, DIAS_PROXIMO_PAGO);
        generaDescuento(valorBase, PORCENTAJE_DESCUENTO);
       // this.fechaProximoPago =calcularFecha(20);
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
    public ComandoPagoTestDataBuilder conAplicaDescuento(boolean aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
        return this;
    }

    public ComandoPagoTestDataBuilder conAplicaConValorBase(double valorBase) {
        this.valorBase = valorBase;
        return this;
    }

    public ComandoPagoTestDataBuilder conAplicaConFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaProximoPago = fechaVencimiento;
        return this;
    }
    public void generarFechaProximoPago(LocalDateTime fechaRegistro, int diasProximoPago) {

        int incrementoDias = 0;
        LocalDateTime fechaActual = LocalDateTime.now();
        while (incrementoDias < diasProximoPago) {
            fechaRegistro = fechaRegistro.plusDays(1);
            if (DayOfWeek.SATURDAY != fechaRegistro.getDayOfWeek()
                    && DayOfWeek.SUNDAY != fechaRegistro.getDayOfWeek()) {
                incrementoDias++;
            }
        }
        this.fechaProximoPago=fechaRegistro;
    }

    public void generaDescuento(double valorBase,double PORCENTAJE_DESCUENTO){
        double valorDescuento= valorBase* PORCENTAJE_DESCUENTO;
        if(this.aplicaDescuento){
            this.valorTotal=this.valorBase-valorDescuento;
        }else{
            this.valorTotal=this.valorBase;
        }
    }

    public ComandoPago build() {
        return new ComandoPago(id,cedulaUsuario,nombre,referenciaPago,aplicaDescuento,valorBase,valorTotal,fechaRegistro, fechaProximoPago);
    }

}
