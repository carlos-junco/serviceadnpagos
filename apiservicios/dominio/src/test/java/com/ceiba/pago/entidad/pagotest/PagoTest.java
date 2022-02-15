package com.ceiba.pago.entidad.pagotest;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.modelo.entidad.cliente.Identificacion;
import com.ceiba.pago.modelo.entidad.cliente.TipoIdentificacion;
import com.ceiba.pago.modelo.entidad.pago.Pago;
import com.ceiba.pago.servicio.excepcionesservicio.ExcepcionDiaNoValido;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PagoTest {

    @Test
    @DisplayName("Deberia crear correctamente el pago")
    void deberiaCrearCorrectamenteElUsusuario() {
        // arrange
        LocalDate fechaRegistro = LocalDate.now();
        Cliente cliente= new Cliente(1L,"Fulano", new Identificacion(TipoIdentificacion.CEDULA,"1083000935"));
        //act
        Pago pago= new PagoTestDataBuilder().conValorFechaRegistro(fechaRegistro).conId(1L).build();
        //assert
        assertEquals(1L, pago.getId());
        //assertTrue(cliente.validaIdentificacionIgual(pago.getCliente()));
        //assertTrue(cliente.validaNombreClienteIgual(pago.getCliente()));
        assertEquals("0000",pago.getReferenciaPago());
        assertEquals(200000,pago.getValorBase());
        assertEquals(170000.0,pago.getValorTotal());
        assertEquals(fechaRegistro, pago.getFechaRegistro());
    }

    @Test
    void validaFechaIncorrectaPagoDomingo(){
        //arrange
        LocalDate fechaDomingo= LocalDate.of(2022,02,13);
        PagoTestDataBuilder pagoTestDataBuilder= new PagoTestDataBuilder().conValorFechaRegistro(fechaDomingo);
        //act-assert
        BasePrueba.assertThrows(()->{
            pagoTestDataBuilder.build();
        }, ExcepcionDiaNoValido.class,"No se puede pagar este día");
    }

    @Test
    void validaFechaIncorrectaPagoSabado(){
        //arrange
        LocalDate fechaSabado= LocalDate.of(2022,02,12);
        PagoTestDataBuilder pagoTestDataBuilder= new PagoTestDataBuilder().conValorFechaRegistro(fechaSabado);
        //act-assert
        BasePrueba.assertThrows(()->{
            pagoTestDataBuilder.build();
        }, ExcepcionDiaNoValido.class,"No se puede pagar este día");

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
    void fallaConReferenciaPagoMayorCuatroCaracteres(){
        //arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conReferenciaPago("123456");

        //act-assert
        BasePrueba.assertThrows(()->{
            pagoTestDataBuilder.build();
        },ExcepcionValorInvalido.class,"La refencia de pago debe tener 4 dígitos");
    }

    @Test
    void fallaConReferenciaPagoMenorCuatroCaracteres(){
        //arrange
        PagoTestDataBuilder pagoTestDataBuilder = new PagoTestDataBuilder().conReferenciaPago("123");

        //act-assert
        BasePrueba.assertThrows(()->{
            pagoTestDataBuilder.build();
        },ExcepcionValorInvalido.class,"La refencia de pago debe tener 4 dígitos");
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
}
