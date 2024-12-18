package com.restaurante.facturacion.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurante.facturacion.productos.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	public Marca findByNombre(String nombre);
}
