package com.restaurante.facturacion.productos.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.facturacion.productos.entity.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {  // Cambiar MesaRepository a Mesa
    List<Mesa> findByNumero(int numero, Pageable page);
}
