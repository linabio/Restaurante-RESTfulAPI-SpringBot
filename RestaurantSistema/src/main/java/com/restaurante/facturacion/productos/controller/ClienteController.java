package com.restaurante.facturacion.productos.controller;

import com.restaurante.facturacion.productos.entity.Cliente;
import com.restaurante.facturacion.productos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Cliente> clientes = clienteService.findAll(pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") int id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Cliente> findByNombre(@RequestParam("nombre") String nombre) {
        Cliente cliente = clienteService.findByNombre(nombre);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente createdCliente = clienteService.create(cliente);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") int id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        Cliente clienteActualizado = clienteService.update(cliente);
        if (clienteActualizado != null) {
            return ResponseEntity.ok(clienteActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean eliminado = clienteService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}