package com.ceiba.pago.modelo.entidad;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;


/**
 * Clase que representa la entidad de dominio de la aplicación
 * @author carlos.junco
 * @version 1.0.0
 */
@Getter
@Setter
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

    private Long id;
    private String cedulaUsuario;
    private String nombre;
    private String referenciaPago;
    private String aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaVencimiento;

    public Pago(Long id, String cedulaUsuario, String nombre, String referenciaPago, String aplicaDescuento, double valorBase, double valorTotal, LocalDateTime fechaRegistro, LocalDateTime fechaVencimiento) {
        validandoArgumentos(cedulaUsuario, nombre, referenciaPago, valorBase, fechaRegistro, fechaVencimiento);
        this.id = id;
        this.cedulaUsuario=cedulaUsuario;
        this.nombre = nombre;
        this.referenciaPago =referenciaPago;
        this.aplicaDescuento=aplicaDescuento;
        this.valorBase=valorBase;
        this.valorTotal=valorTotal;
        this.fechaRegistro = fechaRegistro;
        this.fechaVencimiento = fechaVencimiento;
    }

    private void validandoArgumentos(String cedulaUsuario, String nombre, String referenciaPago, double valorBase, LocalDateTime fechaRegistro, LocalDateTime fechaVencimiento) {
        validarObligatorio(cedulaUsuario,SE_DEBE_INGRESAR_CEDULA);
        validarObligatorio(nombre, SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO);
        validarLongitudMaxima(cedulaUsuario,10,LONGITUD_CEDULA);
        validarLongitudMaxima(nombre,15,NOMBRE_LONGITUD_MAXIMA);
        validarObligatorio(referenciaPago, SE_DEBE_INGRESAR_REFERENCIA_PAGO);
        validarObligatorio(valorBase,SE_DEBE_INGRESAR_VALOR_BASE);
        validarPositivo(valorBase,SE_DEBE_INGRESAR_VALOR_MAYOR_CERO);
        validarObligatorio(fechaRegistro, SE_DEBE_INGRESAR_LA_FECHA_DE_REGISTRO);
        validarMenor(fechaRegistro, fechaVencimiento, VALIDA_FECHAS);
    }
}
