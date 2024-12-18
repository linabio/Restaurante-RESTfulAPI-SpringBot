package com.restaurante.facturacion.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.facturacion.productos.entity.Pedido;
import com.restaurante.facturacion.productos.service.PedidoService;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping
    public ResponseEntity<List<Pedido>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Pedido> pedidos = service.findAll(pageable);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable("id") int id) {
        Pedido pedido = service.findById(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido) {
        Pedido createdPedido = service.create(pedido);
        return new ResponseEntity<>(createdPedido, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable("id") int id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        Pedido pedidoActualizado = service.update(pedido);
        if (pedidoActualizado != null) {
            return ResponseEntity.ok(pedidoActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean eliminado = service.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
