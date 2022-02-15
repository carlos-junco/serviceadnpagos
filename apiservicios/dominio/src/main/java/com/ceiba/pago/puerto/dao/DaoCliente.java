package com.ceiba.pago.puerto.dao;

import com.ceiba.pago.modelo.dto.DtoCliente;
import com.ceiba.pago.modelo.dto.DtoPago;

import java.util.List;

public interface DaoCliente {
    /**
     * Permite listar clientes
     * @return los clientes
     */
    List<DtoCliente> listar();
}
