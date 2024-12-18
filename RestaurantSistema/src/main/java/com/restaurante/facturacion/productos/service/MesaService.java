package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.Categoria;
import com.restaurante.facturacion.productos.entity.Mesa;

public interface MesaService {
	public List<Mesa> findAll(Pageable page);
    List<Mesa> getMesasPorNumero(int numero, int page, int size);
    Mesa createMesa(Mesa mesa);
    Mesa updateMesa(int id, Mesa mesaDetails);
    void deleteMesa(int id);
    
}
