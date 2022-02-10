package com.ceiba.pago.modelo.entidad;
import lombok.Getter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;


/**
 * Clase que representa la entidad de dominio de la aplicación
 * @author carlos.junco
 * @version 1.0.0
 */
@Getter
public class Pago {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO = "Se debe ingresar la fecha de registro";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_VENCIMIENTO = "Se debe ingresar la fecha de vencimiento";
    private static final String SE_DEBE_INGRESAR_CEDULA = "Se debe ingresar la cédula de la persona";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";
    private static final String SE_DEBE_INGRESAR_VALOR_BASE="Se debe ingresar el valor del servicio";
    private static final String SE_DEBE_INGRESAR_REFERENCIA_PAGO="Se debe ingresar referencia de pago";
    private static final String SE_DEBE_INGRESAR_VALOR_MAYOR_CERO="Se debe ingresar un valor mayor a cero";
    private static final String LONGITUD_CEDULA="La cédula debe tener menos de 10 carácteres";
    private static final String NOMBRE_LONGITUD_MAXIMA="El nombre sólo puedo contener m+aximo 15 carácteres";
    private static final String VALIDA_FECHAS="La fecha de registro debe ser menor que la fecha de vecimiento";
    public static final int NUMERO_DIAS_PROXIMO_PAGO = 20;
    public static final double PORCENTAJE_DESCUENTO = 0.15;


    private Long id;
    private String cedulaUsuario;
    private String nombre;
    private String referenciaPago;
    private boolean aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaProximoPago;

    public Pago(Long id, String cedulaUsuario, String nombre, String referenciaPago, boolean aplicaDescuento, double valorBase, LocalDateTime fechaRegistro) {
        validandoArgumentos(cedulaUsuario, nombre, referenciaPago, valorBase, fechaRegistro);
        this.id = id;
        this.cedulaUsuario=cedulaUsuario;
        this.nombre = nombre;
        this.referenciaPago =referenciaPago;
        this.aplicaDescuento=aplicaDescuento;
        this.valorBase=valorBase;
        this.fechaRegistro = fechaRegistro;
        generarFechaProximoPago(fechaRegistro,NUMERO_DIAS_PROXIMO_PAGO);
        generaDescuento(valorBase);
    }

    private void validandoArgumentos(String cedulaUsuario, String nombre, String referenciaPago, double valorBase, LocalDateTime fechaRegistro) {
        validarObligatorio(cedulaUsuario,SE_DEBE_INGRESAR_CEDULA);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
        validarLongitudMaxima(cedulaUsuario,10,LONGITUD_CEDULA);
        validarLongitudMaxima(nombre,15,NOMBRE_LONGITUD_MAXIMA);
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


    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", cedulaUsuario='" + cedulaUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", referenciaPago='" + referenciaPago + '\'' +
                ", aplicaDescuento=" + aplicaDescuento +
                ", valorBase=" + valorBase +
                ", valorTotal=" + valorTotal +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaProximoPago=" + fechaProximoPago +
                '}';
    }
}
