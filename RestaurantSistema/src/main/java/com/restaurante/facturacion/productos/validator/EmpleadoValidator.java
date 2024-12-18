package com.restaurante.facturacion.productos.validator;

import com.restaurante.facturacion.productos.entity.Empleado;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;

public class EmpleadoValidator {

    public static void validate(Empleado empleado) {
 
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }
        if (empleado.getNombre().length() > 100) {
            throw new ValidateServiceException("El nombre no debe tener más de 100 caracteres");
        }

        if (empleado.getDocumento() == null || empleado.getDocumento().trim().isEmpty()) {
            throw new ValidateServiceException("El documento es requerido");
        }
        if (empleado.getDocumento().length() > 15) {
            throw new ValidateServiceException("El documento no debe tener más de 15 caracteres");
        }

        if (empleado.getTelefono() != null && empleado.getTelefono().length() > 15) {
            throw new ValidateServiceException("El teléfono no debe tener más de 15 caracteres");
        }

        if (empleado.getDireccion() != null && empleado.getDireccion().length() > 100) {
            throw new ValidateServiceException("La dirección no debe tener más de 100 caracteres");
        }

        if (empleado.getEmail() != null && empleado.getEmail().length() > 50) {
            throw new ValidateServiceException("El email no debe tener más de 50 caracteres");
        }

        if (empleado.getTipoEmpleado() == null) {
            throw new ValidateServiceException("El tipo de empleado es requerido");
        }
    }
}