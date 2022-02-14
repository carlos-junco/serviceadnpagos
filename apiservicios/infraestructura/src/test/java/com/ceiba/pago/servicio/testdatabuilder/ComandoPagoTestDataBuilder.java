package com.ceiba.pago.servicio.testdatabuilder;

import com.ceiba.pago.comando.ComandoPago;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.modelo.entidad.cliente.Identificacion;
import com.ceiba.pago.modelo.entidad.cliente.TipoIdentificacion;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ComandoPagoTestDataBuilder {
    public static final int DIAS_PROXIMO_PAGO = 20;
    public static final double PORCENTAJE_DESCUENTO = 0.15;
    private Long id;
    private Cliente cliente;
    private String referenciaPago;
    private boolean aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDate fechaRegistro;
    private LocalDate fechaProximoPago;;

    public ComandoPagoTestDataBuilder() {
        this.id=2L;
        this.cliente=new Cliente(1L,"Fulano",new Identificacion(TipoIdentificacion.CEDULA,"1083000935"));
        this.referenciaPago="1111";
        this.aplicaDescuento=true;
        this.valorBase=200000;
        this.fechaRegistro = LocalDate.now();
        generarFechaProximoPago(fechaRegistro, DIAS_PROXIMO_PAGO);
        generaDescuento(valorBase, PORCENTAJE_DESCUENTO);
    }


    public ComandoPagoTestDataBuilder conCliente(Cliente cliente){
        this.cliente=cliente;
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

    public ComandoPagoTestDataBuilder conAplicaConFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaProximoPago = fechaVencimiento;
        return this;
    }
    public void generarFechaProximoPago(LocalDate fechaRegistro, int diasProximoPago) {

        int incrementoDias = 0;
        LocalDate fechaActual = LocalDate.now();
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
        return new ComandoPago(id,cliente,referenciaPago,aplicaDescuento,valorBase,valorTotal,fechaRegistro, fechaProximoPago);
    }

}
