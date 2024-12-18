package com.restaurante.facturacion.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.facturacion.productos.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	public Producto findByNombre(String nombre);
}
