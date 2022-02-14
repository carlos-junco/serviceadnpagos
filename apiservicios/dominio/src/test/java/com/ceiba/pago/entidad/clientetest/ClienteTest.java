package com.ceiba.pago.entidad.clientetest;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.modelo.entidad.cliente.Identificacion;
import com.ceiba.pago.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.pago.servicio.testdatabuilder.PagoTestDataBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    /**
     * mpétodo que se se encarga de crear un nombre de prueba
     * **/

    @Test
    public void deberiaCrearUsuarioNuevo(){

    //arrange
    Cliente cliente= new ClienteTestDataBuilder().build();
    //act-assert
    assertEquals("Carlos",cliente.getNombre());
    // assertEquals();

    }

    @Test
    void deberiaFallarSinNombre(){
        //arrange
        ClienteTestDataBuilder clienteTestDataBuilder= new ClienteTestDataBuilder().conNombre(null);
        //act-assert
        BasePrueba.assertThrows(()->{
            clienteTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class,"El nombre no puede ser un campo vacío");
    }

    @Test
    void deberiaFallarSinTipoIdentificacion(){
        //arrange

        ClienteTestDataBuilder clienteTestDataBuilder= new ClienteTestDataBuilder().conIdentificacion(null);
        //act
        BasePrueba.assertThrows(()->{
            clienteTestDataBuilder.build();

        },ExcepcionValorObligatorio.class,"Tiene que ingresar la identificación  del cliente");

        //assert
    }






}
