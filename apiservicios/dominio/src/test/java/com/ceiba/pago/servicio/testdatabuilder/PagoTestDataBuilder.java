package com.ceiba.pago.servicio.testdatabuilder;


import com.ceiba.pago.modelo.entidad.Pago;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
/**
 * Clase encargada de crear un objeto de prueba
 * **/
public class PagoTestDataBuilder {

    private static final int DIAS_PROXIMO_PAGO=20;
    public static final double PORCENTAJE_DESCUENTO = 0.15;

    private Long  id;
    private String cedulaUsuario;
    private String nombre;
    private String referenciaPago;
    private boolean aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaProximoPago;

    public PagoTestDataBuilder() {
        this.id=1L;
        this.cedulaUsuario="1111";
        this.nombre="xxxx";
        this.referenciaPago ="0000";
        this.fechaRegistro= LocalDateTime.now();
        this.aplicaDescuento=true;
        this.valorBase=200000;
        generarFechaProximoPago(fechaRegistro,DIAS_PROXIMO_PAGO);
        generaDescuento(valorBase,PORCENTAJE_DESCUENTO);
        //this.valorTotal=200000;

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

    public PagoTestDataBuilder conAplicaDescuento(boolean aplicaDescuento){
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
        this.fechaProximoPago =fechaVencimiento;
        return this;
    }

    public PagoTestDataBuilder conValorTotal(double valorTotal){
        this.valorTotal=valorTotal;
        return this;
    }

    public Pago build(){
        return new Pago(id,cedulaUsuario,nombre, referenciaPago,aplicaDescuento,valorBase,fechaRegistro);
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


    // eliminar esté método
    public static void main(String[] args) {
        Pago pago= new PagoTestDataBuilder().build();
        System.out.println(pago.toString());
    }

}
