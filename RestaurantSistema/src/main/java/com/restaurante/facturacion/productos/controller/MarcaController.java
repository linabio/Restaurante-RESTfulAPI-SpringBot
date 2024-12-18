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

import com.restaurante.facturacion.productos.entity.Marca;
import com.restaurante.facturacion.productos.service.MarcaService;

@RestController
@RequestMapping("/v1/marcas")
public class MarcaController {
	
	@Autowired
	private MarcaService service;
	
	// Listar todas las marcas con paginación
	@GetMapping
	public ResponseEntity<List<Marca>> findAll(
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    List<Marca> marcas = service.findAll(pageable);
	    return ResponseEntity.ok(marcas);
	}

    // Buscar una marca por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> findById(@PathVariable("id")  int id) {
        Marca marca = service.findById(id);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Buscar una marca por su nombre
    @GetMapping("/buscar")
    public ResponseEntity<Marca> findByNombre(@RequestParam("nombre") String nombre) {
        Marca marca = service.findByNombre(nombre);
        if (marca != null) {
            return ResponseEntity.ok(marca);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Crear una nueva marca
    @PostMapping
    public ResponseEntity<Marca> create(@RequestBody Marca marca) {
        Marca nuevaMarca = service.create(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMarca);
    }

    // Actualizar una marca existente
    @PutMapping("/{id}")
    public ResponseEntity<Marca> update(@PathVariable("id") int id, @RequestBody Marca marca) {
        marca.setId(id);
        Marca marcaActualizada = service.update(marca);
        if (marcaActualizada != null) {
            return ResponseEntity.ok(marcaActualizada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Eliminar una marca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean eliminado = service.delete(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
