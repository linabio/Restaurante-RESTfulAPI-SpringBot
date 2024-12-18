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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.facturacion.productos.entity.Categoria;
import com.restaurante.facturacion.productos.service.CategoriaService;


@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
		){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Categoria> registros = service.findAll(page);
		return new ResponseEntity<>(registros, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") int id) {
        Categoria categoria = service.findById(id);
        if (categoria != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	
	@GetMapping("buscar/{nombre}")
    public ResponseEntity<Categoria> getByNombre(@PathVariable("nombre") String nombre) {
        Categoria categoria = service.findByNombre(nombre);
        if (categoria != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
	
	
	@PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        Categoria categoriaDb = service.save(categoria);
        //return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDb);
        return new ResponseEntity<>(categoriaDb, HttpStatus.CREATED);
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
