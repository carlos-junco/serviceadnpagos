package com.ceiba.pago.servicio.cliente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.puerto.repositorio.RepositorioCliente;
import com.ceiba.pago.servicio.testdatabuilder.ClienteTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ServicioCrearClienteTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del cliente")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelClliente() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente reposiotrioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(reposiotrioCliente.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioCrearCliente servicioCrearCliente= new ServicioCrearCliente(reposiotrioCliente);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCliente.ejecutar(cliente), ExcepcionDuplicidad.class,"El Cliente ya se encuentra en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear el pago de manera correcta")
    void deberiaCrearElCliente() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
        Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioCliente.crear(cliente)).thenReturn(1L);
        ServicioCrearCliente servicioCrearCliente= new ServicioCrearCliente(repositorioCliente);
        // act
        Long idPago = servicioCrearCliente.ejecutar(cliente);
        //- assert
        assertEquals(1L,idPago);
        Mockito.verify(repositorioCliente, Mockito.times(1)).crear(cliente);
    }

}