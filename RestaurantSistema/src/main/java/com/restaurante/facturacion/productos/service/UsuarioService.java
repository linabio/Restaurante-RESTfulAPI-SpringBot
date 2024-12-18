package com.restaurante.facturacion.productos.service;

import com.restaurante.facturacion.productos.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(int id);
    Usuario findByEmail(String email);
    Usuario create(Usuario usuario);
    Usuario update(Usuario usuario);
    boolean delete(int id);
}
