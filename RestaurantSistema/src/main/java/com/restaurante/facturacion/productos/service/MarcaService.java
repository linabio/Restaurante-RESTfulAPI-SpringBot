package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.Marca;

public interface MarcaService {
	public List<Marca> findAll(Pageable page);
	public Marca findById(int id);
	public Marca findByNombre(String nombre);
	public Marca create(Marca obj);
	public Marca update(Marca obj);
	public boolean delete(int id);
}
