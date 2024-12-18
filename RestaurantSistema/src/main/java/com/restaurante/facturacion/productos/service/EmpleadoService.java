package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.Empleado;


public interface EmpleadoService {
	public List<Empleado> findAll(Pageable page);
	public Empleado findById(int id);
	public Empleado findByNombre(String nombre);
	public List<Empleado> findByNombreContaining(String nombre);
    public Empleado create(Empleado obj);
    public Empleado update(Empleado obj);
    public boolean delete(int id);
}