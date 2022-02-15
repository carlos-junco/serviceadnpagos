package com.ceiba.configuracion;

import com.ceiba.pago.puerto.repositorio.RepositorioCliente;
import com.ceiba.pago.puerto.repositorio.RepositorioPago;
import com.ceiba.pago.servicio.cliente.ServicioCrearCliente;
import com.ceiba.pago.servicio.pago.ServicioCrearPago;
import com.ceiba.pago.servicio.pago.ServicioEliminarPago;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class BeanServicioCliente {

    @Bean
    public ServicioCrearCliente servicioCrearCliente(RepositorioCliente repositorioCliente) {
        return new ServicioCrearCliente(repositorioCliente);
    }

    /*
    @Bean
    public ServicioEliminarPago servicioEliminarPago(RepositorioPago repositorioPago) {
        return new ServicioEliminarPago(repositorioPago);
    }*/
}
