package com.ceiba.pago.modelo.entidad.pago;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.servicio.excepcionesservicio.ExcepcionDiaNoValido;
import lombok.Getter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

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


    private Long id;
    private Cliente cliente;
    private String referenciaPago;
    private boolean aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaProximoPago;

    public Pago(Long id, Cliente cliente, String referenciaPago, boolean aplicaDescuento, double valorBase, LocalDateTime fechaRegistro) {
        validandoArgumentos(referenciaPago, valorBase, fechaRegistro);
        this.id = id;
        this.cliente=cliente;
        this.referenciaPago =referenciaPago;
        this.aplicaDescuento=aplicaDescuento;
        this.valorBase=valorBase;
        this.fechaRegistro = fechaRegistro;
        validaDiaPago(fechaRegistro);
        generarFechaProximoPago(fechaRegistro,NUMERO_DIAS_PROXIMO_PAGO);
        generaDescuento(valorBase);
    }

    private void validandoArgumentos(String referenciaPago, double valorBase, LocalDateTime fechaRegistro) {
        validarObligatorio(referenciaPago, SE_DEBE_INGRESAR_REFERENCIA_PAGO);
        validarObligatorio(valorBase,SE_DEBE_INGRESAR_VALOR_BASE);
        validarPositivo(valorBase,SE_DEBE_INGRESAR_VALOR_MAYOR_CERO);
        validarObligatorio(fechaRegistro, SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO);
    }

    private void generarFechaProximoPago(LocalDateTime fechaRegistro,int numeroDiasProximoPago){
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

    public void validaDiaPago(LocalDateTime fechaRegistro){
        if(fechaRegistro.getDayOfWeek().equals(DayOfWeek.SATURDAY)|| fechaRegistro.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ExcepcionDiaNoValido(DIA_NO_VALIDO);
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
