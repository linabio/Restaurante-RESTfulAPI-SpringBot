package com.restaurante.facturacion.productos.validator;

import com.restaurante.facturacion.productos.entity.Marca;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;

public class MarcaValidator {
	public static void validate(Marca obj) {
		if(obj.getNombre() == null || obj.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(obj.getNombre().length() >= 100) {
			throw new ValidateServiceException("El nombre no debe tener más de 100 caracteres");
		}
	}
}
