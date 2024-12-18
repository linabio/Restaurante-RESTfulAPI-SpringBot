package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.TipoEmpleado;

public interface TipoEmpleadoService {
	public List<TipoEmpleado> findAll(Pageable page);
	public TipoEmpleado findById(int id);
	public TipoEmpleado findByNombre(String nombre);
    public TipoEmpleado create(TipoEmpleado obj);
    public TipoEmpleado update(TipoEmpleado obj);
    public boolean delete(int id);
}