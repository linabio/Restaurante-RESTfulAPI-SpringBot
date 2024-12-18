package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.Categoria;


public interface CategoriaService {
	public List<Categoria> findAll(Pageable page);
	public Categoria findById(int id);
	public Categoria findByNombre(String nombre);
	public Categoria save(Categoria obj);
    public boolean delete(int id);

}
