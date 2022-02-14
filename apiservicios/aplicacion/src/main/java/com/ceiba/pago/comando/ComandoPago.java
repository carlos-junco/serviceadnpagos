package com.ceiba.pago.comando;



import com.ceiba.pago.modelo.entidad.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPago {
    private Long id;
    private Cliente cliente;
    private String referenciaPago;
    private boolean aplicaDescuento;
    private double valorBase;
    private double valorTotal;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaProximoPago;
}
