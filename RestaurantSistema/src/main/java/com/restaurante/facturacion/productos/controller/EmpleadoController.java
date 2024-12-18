package com.restaurante.facturacion.productos.controller;

import com.restaurante.facturacion.productos.entity.Empleado;
import com.restaurante.facturacion.productos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Empleado> empleados = empleadoService.findAll(pageable);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> findById(@PathVariable("id") int id) {
        Empleado empleado = empleadoService.findById(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Empleado>> findByNombreContaining(@RequestParam("nombre") String nombre) {
        List<Empleado> empleados = empleadoService.findByNombreContaining(nombre);
        if (empleados != null && !empleados.isEmpty()) {
            return ResponseEntity.ok(empleados);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Empleado> create(@RequestBody Empleado empleado) {
        Empleado createdEmpleado = empleadoService.create(empleado);
        return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable("id") int id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        Empleado empleadoActualizado = empleadoService.update(empleado);
        if (empleadoActualizado != null) {
            return ResponseEntity.ok(empleadoActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean eliminado = empleadoService.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
