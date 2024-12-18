package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.Pedido;

public interface PedidoService {
	public List<Pedido> findAll(Pageable page);
	public Pedido findById(int id);
	public Pedido create(Pedido obj);
	public Pedido update(Pedido obj);
	public boolean delete(int id);
}


