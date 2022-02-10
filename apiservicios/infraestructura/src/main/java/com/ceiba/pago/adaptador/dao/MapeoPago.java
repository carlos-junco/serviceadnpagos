package com.ceiba.pago.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pago.modelo.dto.DtoPago;
import org.springframework.jdbc.core.RowMapper;

public class MapeoPago implements RowMapper<DtoPago>, MapperResult {

    @Override
    public DtoPago mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String cedulaUsuario=resultSet.getString("cedula_usuario");
        String nombre = resultSet.getString("nombre");
        String referenciaPago = resultSet.getString("referencia_pago");
        String aplicaDescuento=resultSet.getString("aplica_descuento");
        double valorBase=resultSet.getDouble("valor_base");
        double valorTotal=resultSet.getDouble("valor_total");
        LocalDateTime fechaRegistro = extraerLocalDateTime(resultSet, "fecha_registro");
        LocalDateTime fechaVencimiento = extraerLocalDateTime(resultSet, "fecha_vencimiento");
        return new DtoPago(id,cedulaUsuario,nombre,referenciaPago,aplicaDescuento,valorBase,valorTotal,fechaRegistro,fechaVencimiento);
    }

}


