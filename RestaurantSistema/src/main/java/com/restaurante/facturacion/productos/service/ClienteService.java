package com.restaurante.facturacion.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.restaurante.facturacion.productos.entity.Cliente;

public interface ClienteService {
	public List<Cliente> findAll(Pageable page);
	public Cliente findById(int id);
	public Cliente findByNombre(String nombre);
    public Cliente create(Cliente obj);
    public Cliente update(Cliente obj);
    public boolean delete(int id);
}