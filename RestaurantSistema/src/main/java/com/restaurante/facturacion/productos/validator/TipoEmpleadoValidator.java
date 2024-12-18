package com.restaurante.facturacion.productos.validator;

import com.restaurante.facturacion.productos.entity.TipoEmpleado;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;

public class TipoEmpleadoValidator {
	 public static void validate(TipoEmpleado tipoEmpleado) {
	        if (tipoEmpleado.getNombre() == null || tipoEmpleado.getNombre().trim().isEmpty()) {
	            throw new ValidateServiceException("El nombre es requerido");
	        }
	        if (tipoEmpleado.getNombre().length() > 100) {
	            throw new ValidateServiceException("El nombre no debe tener más de 100 caracteres");
	        }
	    }
}