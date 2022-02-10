update pagos
set nombre = :nombre,
	refencia_pago = :referenciaPago,
	fecha_registro = :fechaRegistro
where id = :id