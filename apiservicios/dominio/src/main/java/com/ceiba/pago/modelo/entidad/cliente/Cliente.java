package com.ceiba.pago.modelo.entidad.cliente;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Cliente {

    public static final String EL_NOMBRE_NO_PUEDE_SER_UN_CAMPO_VACIO = "El nombre no puede ser un campo vacío";
    public static final String TIENE_QUE_INGRESAR_LA_IDENTIFICACIÓN_DEL_CLIENTE = "Tiene que ingresar la identificación  del cliente";
    private Long id;
    private String nombre;
    private Identificacion identificacion;

    public Cliente(Long id, String nombre,Identificacion identificacion){
        validarObligatorio(nombre, EL_NOMBRE_NO_PUEDE_SER_UN_CAMPO_VACIO);
        validarObligatorio(identificacion, TIENE_QUE_INGRESAR_LA_IDENTIFICACIÓN_DEL_CLIENTE);
        this.id=id;
        this.identificacion=identificacion;
        this.nombre=nombre;
    }

    public boolean validaIdentificacionIgual(Cliente cliente){
       return this.identificacion.validaIdentificacion(cliente.getIdentificacion());
    }

    public boolean validaNombreClienteIgual(Cliente cliente){
        return this.nombre.equals(cliente.getNombre());
    }
}
