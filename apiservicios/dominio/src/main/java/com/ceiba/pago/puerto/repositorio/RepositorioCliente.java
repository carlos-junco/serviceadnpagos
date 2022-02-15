package com.ceiba.pago.puerto.repositorio;

import com.ceiba.pago.modelo.entidad.cliente.Cliente;

public interface RepositorioCliente {

    Long crear(Cliente cliente);

    void actualizar(Cliente usuario);

    void eliminar(Long id);

    boolean existe(String id);

    boolean existePorId(Long id);
}
