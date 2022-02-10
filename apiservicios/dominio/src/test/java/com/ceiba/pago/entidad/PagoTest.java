package com.ceiba.pago.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudMaxima;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.modelo.entidad.Pago;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;


import static com.ceiba.pago.servicio.testdatabuilder.CalcularFechaTestDataBuilder.calcularFecha;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PagoTest {

    @Test
    @DisplayName("Deberia crear correctamente el usuario")
    void deberiaCrearCorrectamenteElUsusuario() {
        // arrange
        LocalDateTime fechaRegistro = LocalDateTime.now();
        //act
        LocalDateTime fechaVencimiento=calcularFecha(20);
        Pago pago= new PagoTestDataBuilder().conValorFechaRegistro(fechaRegistro).conValorFechaVencimiento(fechaVencimiento).conId(1L).build();
        //assert
        assertEquals(1L, pago.getId());
        assertEquals("1111",pago.getCedulaUsuario());
        assertEquals("xxxx", pago.getNombre());
        assertEquals("0000",pago.getReferenciaPago());
        assertEquals(200000,pago.getValorBase());
        assertEquals(200000,pago.getValorTotal());
        assertEquals(fechaRegistro, pago.getFechaRegistro());
        assertEquals(fechaVencimiento,pago.getFechaVencimiento());
    }

    @Test
    void deberiaFallaSinCedulaUsuario(){
        //arrange
        PagoTestDataBuilder pagoTestDataBuilder= new PagoTestDataBuilder().conCedulaUsuario(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(()->{
            pagoTestDataBuilder.build();
        },ExcepcionValorObligatorio.class,"Se debe ingresar la cédula de la persona");
    }

    @Test
    void deberiaFallarSinNombreDeUsuario() {
        //Arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conNombre(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoTestDataBuilder.build();
                }, ExcepcionValorObligatorio.class, "Se debe ingresar el nombre de usuario");
    }

    @Test
    void deberiaFallarSinReferenciaPago(){
        //arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conReferenciaPago(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(()->{
                     pagoTestDataBuilder.build();
                }, ExcepcionValorObligatorio.class,"Se debe ingresar referencia de pago");
    }

    @Test
    void deberiaFallarSinFechaRegistro() {
        //Arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conValorFechaRegistro(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    pagoTestDataBuilder.build();
                }, ExcepcionValorObligatorio.class, "Se debe ingresar la fecha de registro");
    }

    @Test
    void ValorBaseCeroFalla(){
        //Arrange
        Double prof=null;
        PagoTestDataBuilder pagoTestDataBuilder= new PagoTestDataBuilder().conValorBase(0).conId(1L);
        //Act-assert
        BasePrueba.assertThrows(()->{
            pagoTestDataBuilder.build();
        }, ExcepcionValorInvalido.class,"Se debe ingresar un valor mayor a cero");

    }
    @Test
    void debeFallarLongitudMaximaCedula(){
        //arrange
        PagoTestDataBuilder pagoTestDataBuilder= new PagoTestDataBuilder().conCedulaUsuario("11111111111").conId(1L);
        //act-assert
        BasePrueba.assertThrows(()->{
            pagoTestDataBuilder.build();
        }, ExcepcionLongitudMaxima.class,"La cédula debe tener menos de 10 carácteres");
    }

}
