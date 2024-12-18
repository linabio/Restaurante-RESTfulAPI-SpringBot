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

import com.restaurante.facturacion.productos.entity.TipoEmpleado;
import com.restaurante.facturacion.productos.service.TipoEmpleadoService;

@RestController
@RequestMapping("/v1/tipoEmpleado")
public class TipoEmpleadoController {
	@Autowired
    private TipoEmpleadoService tipoEmpleadoService;

    @GetMapping
    public ResponseEntity<List<TipoEmpleado>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<TipoEmpleado> tipoEmpleado = tipoEmpleadoService.findAll(pageable);
        return new ResponseEntity<>(tipoEmpleado, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEmpleado> findById(@PathVariable("id")  int id) {
    	TipoEmpleado producto = tipoEmpleadoService.findById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<TipoEmpleado> findByNombre(@RequestParam("nombre") String nombre) {
    	TipoEmpleado marca = tipoEmpleadoService.findByNombre(nombre);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PostMapping
    public ResponseEntity<TipoEmpleado> create(@RequestBody TipoEmpleado tipoEmpleado) {
        TipoEmpleado createdTipoEmpleado = tipoEmpleadoService.create(tipoEmpleado);
        return new ResponseEntity<>(createdTipoEmpleado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEmpleado> update(@PathVariable("id") int id, @RequestBody TipoEmpleado empleado) {
    	empleado.setId(id);
        TipoEmpleado empleadoActualizado = tipoEmpleadoService.update(empleado);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean eliminado = tipoEmpleadoService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
