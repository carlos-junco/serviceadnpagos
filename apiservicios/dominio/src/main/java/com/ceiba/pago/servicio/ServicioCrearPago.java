package com.ceiba.pago.servicio;

import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import java.time.LocalDateTime;

/***
 * clase encargada de manejar la lógica de negocio para crear pagos
 * @author carlos.junco
 * @version 1.0.0
 */
public class ServicioCrearPago {

    public static final String EL_PAGO_YA_SE_ENCUENTRA_EN_EL_SISTEMA = "El pago ya se encuentra en el sistema";
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
        //validarDiaPago(pago);
        return this.repositorioPago.crear(pago);
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


    /*

    private void validarDiaPago(LocalDateTime fechaRegistro){
        if(fechaRegistro.getDayOfWeek().equals(DayOfWeek.SATURDAY) || fechaRegistro.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ExcepcionDiaNoValido(DIA_NO_VALIDO);
        }
        pago.setFechaRegistro(fechaRegistro);
    }


     */



}
