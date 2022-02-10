package com.ceiba.pago.servicio.testdatabuilder;


import com.ceiba.pago.modelo.entidad.Pago;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.ceiba.pago.servicio.utilidadesservicio.CalcularFechaVencimiento.calcularFecha;


/**
 * Clase encargada de crear un objeto de prueba
 * **/
public class PagoTestDataBuilder {

    private Long  id;
    private String cedulaUsuario;
    private String nombre;
    private String referenciaPago;
    private String aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaVencimiento;

    public PagoTestDataBuilder() {
        this.id=1L;
        this.cedulaUsuario="1111";
        this.nombre="xxxx";
        this.referenciaPago ="0000";
        this.fechaRegistro= LocalDateTime.now();
        this.fechaVencimiento= calcularFecha(20);
        this.aplicaDescuento="S";
        this.valorBase=200000;
        this.valorTotal=200000;

    }
    // creamos los métodos que setean una propiedad y retornan el objeto;

    public PagoTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public PagoTestDataBuilder conCedulaUsuario(String cedulaUsuario){
        this.cedulaUsuario=cedulaUsuario;
        return this;
    }

    public PagoTestDataBuilder conNombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public PagoTestDataBuilder conAplicaDescuento(String aplicaDescuento){
        this.aplicaDescuento=aplicaDescuento;
        return this;
    }

    public PagoTestDataBuilder conReferenciaPago(String referenciaPago){
        this.referenciaPago =referenciaPago;
        return this;
    }

    public PagoTestDataBuilder conValorBase(double valorBase){
        this.valorBase=valorBase;
        return this;
    }

    public PagoTestDataBuilder conValorFechaRegistro(LocalDateTime fechaRegistro){
        this.fechaRegistro=fechaRegistro;
        return this;
    }


    public PagoTestDataBuilder conValorFechaVencimiento(LocalDateTime fechaVencimiento){
        this.fechaVencimiento=fechaVencimiento;
        return this;
    }

    public PagoTestDataBuilder conValorTotal(double valorTotal){
        this.valorTotal=valorTotal;
        return this;
    }

    public Pago build(){
        return new Pago(id,cedulaUsuario,nombre, referenciaPago,aplicaDescuento,valorBase,valorTotal,fechaRegistro,fechaVencimiento);
    }

    // eliminar esté método
    public static void main(String[] args) {
        Pago pago= new PagoTestDataBuilder().build();
        System.out.println(pago.toString());
    }

}
