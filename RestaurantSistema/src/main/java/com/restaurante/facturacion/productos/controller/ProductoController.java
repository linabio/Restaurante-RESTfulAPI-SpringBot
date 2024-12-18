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

import com.restaurante.facturacion.productos.entity.Producto;
import com.restaurante.facturacion.productos.service.ProductoService;

@RestController
@RequestMapping("/v1/Producto")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
    public ResponseEntity<List<Producto>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Producto> productos = productoService.findAll(pageable);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable("id") int id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Producto> findByNombre(@RequestParam("nombre") String nombre) {
        Producto producto = productoService.findByNombre(nombre);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        Producto createdProducto = productoService.create(producto);
        return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable("id") int id, @RequestBody Producto producto) {
        producto.setId(id);
        Producto productoActualizado = productoService.update(producto);
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean eliminado = productoService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
	
}
