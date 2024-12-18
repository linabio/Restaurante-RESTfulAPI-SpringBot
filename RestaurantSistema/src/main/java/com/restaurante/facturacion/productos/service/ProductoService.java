package com.restaurante.facturacion.productos.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.restaurante.facturacion.productos.entity.Producto;

public interface ProductoService {
	public List<Producto> findAll(Pageable page);
	public Producto findById(int id);
	public Producto findByNombre(String nombre);
	public Producto create(Producto obj);
	public Producto update(Producto obj);
	public boolean delete(int id);
}
