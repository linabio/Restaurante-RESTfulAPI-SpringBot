package com.restaurante.facturacion.productos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.facturacion.productos.entity.Empleado;

@Repository
public interface EmpleadoRepository  extends JpaRepository<Empleado, Integer>{
	public Empleado findByNombre(String nombre);
	public List<Empleado> findByNombreContaining (String nombre);
}