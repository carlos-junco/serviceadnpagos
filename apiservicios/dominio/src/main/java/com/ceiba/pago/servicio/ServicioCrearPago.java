package com.ceiba.pago.servicio;

import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pago.servicio.excepcionesservicio.ExcepcionDiaNoValido;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


import static com.ceiba.pago.servicio.utilidadesservicio.CalcularFechaVencimiento.calcularFecha;

/***
 * clase encargada de manejar la lógica de negocio para crear pagos
 * @author carlos.junco
 * @version 1.0.0
 */

public class ServicioCrearPago {

    public static final int NUMERO_DIAS_VENCIMIENTO_PROXIMO_PAGO =20;
    public static final String EL_PAGO_YA_SE_ENCUENTRA_EN_EL_SISTEMA = "El pago ya se encuentra en el sistema";
    public static final double PORCENTAJE_DESCUENTO=0.15;
    private static final String DIA_NO_VALIDO="No se puede pagar este día";


    private final RepositorioPago repositorioPago;
    /**
     * constructor de la clase, la cual recibe un objeto tipo RepositorioPago
     * @param repositorioPago
     */
    public ServicioCrearPago(RepositorioPago repositorioPago) {
        this.repositorioPago = repositorioPago;
    }
    /**
     * Método ejecutar que se encarga de realizar el proceso de pago
     * @param  pago
     * @return id
    **/
    public Long ejecutar(Pago pago) {
        validarExistenciaPrevia(pago);
        validarDiaPago(pago);
        pago.setFechaVencimiento(calcularFecha(NUMERO_DIAS_VENCIMIENTO_PROXIMO_PAGO));
        valorDescuento(pago);
        return this.repositorioPago.crear(pago);
    }
    /**
     * Método valorDescuento  que se encarga de aplicar un descuento a un  pago
     * de acuerdo a si el campo de aplicaDescuento está en s o en n
     * @param pago
     * @return void
     * **/

    private void valorDescuento(Pago pago) {
        double valorDescuento= pago.getValorBase()*PORCENTAJE_DESCUENTO;
        if(pago.getAplicaDescuento().equalsIgnoreCase("S")){
            pago.setValorTotal(pago.getValorBase()- valorDescuento);
        }
    }
    /**
     * método que valida por el id ,  si un pago se encuentra realizado
     * @param  pago
     * **/
    private void validarExistenciaPrevia(Pago pago) {
        boolean existe = this.repositorioPago.existePorId(pago.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_PAGO_YA_SE_ENCUENTRA_EN_EL_SISTEMA);
        }
    }
    /**
     * método que valida que el pago se realice en días hábiles, si es sábado o domingo lanzaz una excepción
     * @params pago
     * @Exception ExceptionmDiaNoValido
     * **/

    private void validarDiaPago(Pago pago){
        LocalDateTime fechaActual=LocalDateTime.now();
        if(fechaActual.getDayOfWeek().equals(DayOfWeek.SATURDAY) || fechaActual.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ExcepcionDiaNoValido(DIA_NO_VALIDO);
        }
        pago.setFechaRegistro(fechaActual);
    }
}
