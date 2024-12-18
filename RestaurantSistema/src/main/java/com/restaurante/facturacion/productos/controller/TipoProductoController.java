package com.restaurante.facturacion.productos.controller;

import com.restaurante.facturacion.productos.entity.TipoProducto;
import com.restaurante.facturacion.productos.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tiposProductos")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping
    public ResponseEntity<List<TipoProducto>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TipoProducto> tipoProductos = tipoProductoService.findAll(pageable);
        return new ResponseEntity<>(tipoProductos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoProducto> findById(@PathVariable("id")  int id) {
    	TipoProducto producto = tipoProductoService.findById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoProducto> findByNombre(@RequestParam("nombre") String nombre) {
    	TipoProducto marca = tipoProductoService.findByNombre(nombre);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping
    public ResponseEntity<TipoProducto> create(@RequestBody TipoProducto tipoProducto) {
        TipoProducto createdTipoProducto = tipoProductoService.create(tipoProducto);
        return new ResponseEntity<>(createdTipoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoProducto> update(@PathVariable("id") int id, @RequestBody TipoProducto producto) {
    	producto.setId(id);
        TipoProducto productoActualizado = tipoProductoService.update(producto);
        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean eliminado = tipoProductoService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
