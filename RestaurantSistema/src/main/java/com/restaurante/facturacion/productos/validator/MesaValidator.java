package com.restaurante.facturacion.productos.validator;

import com.restaurante.facturacion.productos.entity.Mesa;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;

public class MesaValidator {
    public static void validate(Mesa mesa) {
        if (mesa.getNumero() <= 0) {
            throw new ValidateServiceException("El número de la mesa debe ser mayor a cero.");
        }
        if (mesa.getCapacidad() <= 0) {
            throw new ValidateServiceException("La capacidad de la mesa debe ser mayor a cero.");
        }
        if (mesa.getEstado() < 0 || mesa.getEstado() > 1) {
            throw new ValidateServiceException("El estado de la mesa debe ser 0 o 1.");
        }
    }
}
