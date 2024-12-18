package com.restaurante.facturacion.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.facturacion.productos.entity.TipoProducto;

@Repository
public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer>{
	public TipoProducto findByNombre(String nombre);
}
