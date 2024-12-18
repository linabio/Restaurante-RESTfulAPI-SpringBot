package com.restaurante.facturacion.productos.validator;

import com.restaurante.facturacion.productos.entity.Cliente;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;

public class ClienteValidator {

    public static void validate(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }
        if (cliente.getNombre().length() > 100) {
            throw new ValidateServiceException("El nombre no debe tener más de 100 caracteres");
        }

        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            throw new ValidateServiceException("El apellido es requerido");
        }
        if (cliente.getApellido().length() > 100) {
            throw new ValidateServiceException("El apellido no debe tener más de 100 caracteres");
        }
    }
}