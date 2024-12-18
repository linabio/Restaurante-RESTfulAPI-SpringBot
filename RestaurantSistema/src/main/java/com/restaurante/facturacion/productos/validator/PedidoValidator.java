package com.restaurante.facturacion.productos.validator;

import com.restaurante.facturacion.productos.entity.Pedido;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;

public class PedidoValidator {
	public static void validate(Pedido obj) {
		if (obj.getCliente() == null) {
            throw new ValidateServiceException("El cliente es requerido");
        }
        
        // Validar empleado
        if (obj.getEmpleado() == null) {
            throw new ValidateServiceException("El empleado es requerido");
        }
        
        // Validar estado
        if (obj.getEstado() < 0 || obj.getEstado() > 3) { 
            throw new ValidateServiceException("El estado debe estar entre 0 y 3");
        }
        
        // Validar total
        if (obj.getTotal() == null || obj.getTotal() <= 0) {
            throw new ValidateServiceException("El total debe ser mayor a 0");
        }
	}
}
