package com.restaurante.facturacion.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restaurante.facturacion.productos.entity.Categoria;
import com.restaurante.facturacion.productos.entity.Cliente;
import com.restaurante.facturacion.productos.entity.Mesa;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.service.MesaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

	@GetMapping
	public ResponseEntity<List<Mesa>> getAll(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
		){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Mesa> registros = mesaService.findAll(page);
		return new ResponseEntity<>(registros, HttpStatus.OK);
	}
	
	
	@GetMapping("/buscar")
    public ResponseEntity<List<Mesa>> getMesasPorNumero(
            @RequestParam(name = "numero", required = false, defaultValue = "1") Integer numero,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        try {
            List<Mesa> mesas = mesaService.getMesasPorNumero(numero, page, size);
            return ResponseEntity.ok(mesas);
        } catch (ValidateServiceException e) {
            log.error("Error en validación: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (NoDataFoundException e) {
            log.error("Error: No se encontraron datos - {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (GeneralServiceException e) {
            log.error("Error general en el servicio: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
        try {
            Mesa nuevaMesa = mesaService.createMesa(mesa);
            return ResponseEntity.ok(nuevaMesa);
        } catch (ValidateServiceException e) {
            log.error("Error en validación: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (GeneralServiceException e) {
            log.error("Error general en el servicio: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Mesa> updateMesa(@PathVariable("id") Integer id, @RequestBody Mesa mesaDetails) {
//        try {
//            Mesa mesaActualizada = mesaService.updateMesa(id, mesaDetails);
//            return ResponseEntity.ok(mesaActualizada);
//        } catch (ValidateServiceException e) {
//            log.error("Error en validación: {}", e.getMessage());
//            return ResponseEntity.badRequest().build();
//        } catch (NoDataFoundException e) {
//            log.error("Error: No se encontraron datos - {}", e.getMessage());
//            return ResponseEntity.notFound().build();
//        } catch (GeneralServiceException e) {
//            log.error("Error general en el servicio: {}", e.getMessage(), e);
//            return ResponseEntity.status(500).build();
//        }
//    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> update(@PathVariable("id") int id, @RequestBody Mesa mesa) {
    	mesa.setId(id);
    	Mesa mesaActualizada = mesaService.updateMesa(id,mesa);
        if (mesaActualizada != null) {
            return ResponseEntity.ok(mesaActualizada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable("id") Integer id) {
        try {
            mesaService.deleteMesa(id);
            return ResponseEntity.noContent().build();
        } catch (NoDataFoundException e) {
            log.error("Error: No se encontraron datos - {}", e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (GeneralServiceException e) {
            log.error("Error general en el servicio: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}