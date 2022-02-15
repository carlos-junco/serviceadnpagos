package com.ceiba.pago.servicio.cliente;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import com.ceiba.pago.modelo.entidad.pago.Pago;
import com.ceiba.pago.puerto.repositorio.RepositorioCliente;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;

/***
 * clase encargada de manejar la lógica de negocio para crear clientes
 * @author carlos.junco
 * @version 1.0.0
 */
public class ServicioCrearCliente {

    public static final String EL_CLIENTE_YA_SE_ENCUENTRA_EN_EL_SISTEMA = "El Cliente ya se encuentra en el sistema";

    private final RepositorioCliente repositorioCliente;

    public ServicioCrearCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    public Long ejecutar(Cliente cliente) {
        validarExistenciaPrevia(cliente);
        return this.repositorioCliente.crear(cliente);
    }

    private void validarExistenciaPrevia(Cliente cliente) {
        boolean existe = this.repositorioCliente.existePorId(cliente.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_CLIENTE_YA_SE_ENCUENTRA_EN_EL_SISTEMA);
        }
    }
}
