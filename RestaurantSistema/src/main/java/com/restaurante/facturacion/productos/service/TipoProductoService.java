package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.TipoProducto;


public interface TipoProductoService {
	public List<TipoProducto> findAll(Pageable page);
	public TipoProducto findById(int id);
	public TipoProducto findByNombre(String nombre);
    public TipoProducto create(TipoProducto obj);
    public TipoProducto update(TipoProducto obj);
    public boolean delete(int id);
}