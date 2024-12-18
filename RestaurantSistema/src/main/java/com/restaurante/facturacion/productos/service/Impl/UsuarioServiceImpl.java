package com.restaurante.facturacion.productos.service.Impl;

import com.restaurante.facturacion.productos.entity.Usuario;
import com.restaurante.facturacion.productos.repository.UsuarioRepository;
import com.restaurante.facturacion.productos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario con email " + email + " no encontrado"));
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
        return true;
    }
}
