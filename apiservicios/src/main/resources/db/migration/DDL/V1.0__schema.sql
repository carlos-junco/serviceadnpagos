create table pagos (
 id int not null auto_increment primary key,
 cedula_usuario varchar(15) not null,
 nombre varchar(50) not null,
 referencia_pago varchar(45) not null,
 aplica_descuento varchar(2) not null,
 valor_base dec(9,3) not null,
 valor_total dec(9,3) not null,
 fecha_registro datetime not null,
 fecha_vencimiento datetime not null
);
