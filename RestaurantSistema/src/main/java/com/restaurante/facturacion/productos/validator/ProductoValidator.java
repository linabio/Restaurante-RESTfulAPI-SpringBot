package com.restaurante.facturacion.productos.validator;

import com.restaurante.facturacion.productos.entity.Producto;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;

public class ProductoValidator {
	public static void validate(Producto obj) {
		if(obj.getNombre() == null || obj.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(obj.getNombre().length() >= 100) {
			throw new ValidateServiceException("El nombre no debe tener más de 100 caracteres");
		}
	}
}
