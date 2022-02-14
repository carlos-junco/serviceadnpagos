package com.ceiba.pago.modelo.entidad.pago;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.servicio.excepcionesservicio.ExcepcionDiaNoValido;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.time.DayOfWeek;
import java.time.LocalDate;


import static com.ceiba.dominio.ValidadorArgumento.*;


/**
 * Clase que representa la entidad de dominio de la aplicación
 * @author carlos.junco
 * @version 1.0.
 */
@Getter
public class Pago {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO = "Se debe ingresar la fecha de registro";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_VENCIMIENTO = "Se debe ingresar la fecha de vencimiento";
    private static final String SE_DEBE_INGRESAR_VALOR_BASE="Se debe ingresar el valor del servicio";
    private static final String SE_DEBE_INGRESAR_REFERENCIA_PAGO="Se debe ingresar referencia de pago";
    private static final String SE_DEBE_INGRESAR_VALOR_MAYOR_CERO="Se debe ingresar un valor mayor a cero";
    private static final String NOMBRE_LONGITUD_MAXIMA="El nombre sólo puedo contener m+aximo 15 carácteres";
    public static final int NUMERO_DIAS_PROXIMO_PAGO = 20;
    public static final double PORCENTAJE_DESCUENTO = 0.15;
    public static final String DIA_NO_VALIDO="No se puede pagar este día";
    public static final String LA_REFENCIA_DE_PAGO_DEBE_TENER_4_DÍGITOS = "La refencia de pago debe tener 4 dígitos";


    private Long id;
    private Cliente cliente;
    private String referenciaPago;
    private boolean aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDate fechaRegistro;
    private LocalDate fechaProximoPago;

    public Pago(Long id, Cliente cliente, String referenciaPago, boolean aplicaDescuento, double valorBase, LocalDate fechaRegistro) {
        validandoArgumentos(referenciaPago, valorBase, fechaRegistro);
        this.id = id;
        this.cliente=cliente;
        this.referenciaPago =referenciaPago;
        this.aplicaDescuento=aplicaDescuento;
        this.valorBase=valorBase;
        this.fechaRegistro = fechaRegistro;
        validaDiaPagoNoFinSemana(fechaRegistro);
        generarFechaProximoPago(fechaRegistro,NUMERO_DIAS_PROXIMO_PAGO);
        generaDescuento(valorBase);
    }

    private void validandoArgumentos(String referenciaPago, double valorBase, LocalDate fechaRegistro) {
        validarObligatorio(referenciaPago, SE_DEBE_INGRESAR_REFERENCIA_PAGO);
        validaCantidadCaracteresReferenciaPago(referenciaPago,LA_REFENCIA_DE_PAGO_DEBE_TENER_4_DÍGITOS);
        validarObligatorio(valorBase,SE_DEBE_INGRESAR_VALOR_BASE);
        validarPositivo(valorBase,SE_DEBE_INGRESAR_VALOR_MAYOR_CERO);
        validarObligatorio(fechaRegistro, SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO);
    }

    private void generarFechaProximoPago(LocalDate fechaRegistro,int numeroDiasProximoPago){
        int incrementoDias = 0;
        while (incrementoDias < numeroDiasProximoPago) {
            fechaRegistro = fechaRegistro.plusDays(1);
            if (DayOfWeek.SATURDAY != fechaRegistro.getDayOfWeek()
                    && DayOfWeek.SUNDAY != fechaRegistro.getDayOfWeek()) {
                incrementoDias++;
            }
        }
        this.fechaProximoPago =fechaRegistro;
    }

    public void generaDescuento(double valorBase){
        double valorDescuento= valorBase* PORCENTAJE_DESCUENTO;
        if(this.aplicaDescuento){
            this.valorTotal=this.valorBase-valorDescuento;
        }else{
            this.valorTotal=this.valorBase;
        }
    }

    public void validaDiaPagoNoFinSemana(LocalDate fechaRegistro){
        if(fechaRegistro.getDayOfWeek().equals(DayOfWeek.SATURDAY)|| fechaRegistro.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ExcepcionDiaNoValido(DIA_NO_VALIDO);
        }
    }

    public void validaCantidadCaracteresReferenciaPago(String referenciaPago,String mensaje){
       if(referenciaPago.length()>4 || referenciaPago.length()<4) {
           throw new ExcepcionValorInvalido(mensaje);
       }
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                " cedula" + cliente.getIdentificacion().getNumeroIdentificacion()+
                ", referenciaPago='" + referenciaPago + '\'' +
                ", aplicaDescuento=" + aplicaDescuento +
                ", valorBase=" + valorBase +
                ", valorTotal=" + valorTotal +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaProximoPago=" + fechaProximoPago +
                '}';
    }
}
