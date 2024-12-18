package com.restaurante.facturacion.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.facturacion.productos.entity.TipoEmpleado;

@Repository
public interface TipoEmpleadoRepository extends JpaRepository<TipoEmpleado, Integer> {
	public TipoEmpleado findByNombre(String nombre);
}