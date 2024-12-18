package com.restaurante.facturacion.productos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.facturacion.productos.entity.Producto;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.ProductoRepository;
import com.restaurante.facturacion.productos.service.ProductoService;
import com.restaurante.facturacion.productos.validator.ProductoValidator;

@Service
public class ProductoServiceImpl implements ProductoService{
	@Autowired
	private ProductoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll(Pageable page) {
		try {
            return repository.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener todos los productos", e);
        }
	}

	@Override
    @Transactional(readOnly = true)
    public Producto findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No se encontró el producto con ID: " + id));
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar el producto por ID", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findByNombre(String nombre) {
        try {
            return repository.findByNombre(nombre);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar el producto por nombre", e);
        }
    }

    @Override
    @Transactional
    public Producto create(Producto obj) {
        try {
            ProductoValidator.validate(obj);
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al crear el producto", e);
        }
    }

    @Override
    @Transactional
    public Producto update(Producto obj) {
        try {
            ProductoValidator.validate(obj);
            if (!repository.existsById(obj.getId())) {
                throw new NoDataFoundException("No existe el producto con ID: " + obj.getId());
            }
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al actualizar el producto", e);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        try {
            if (!repository.existsById(id)) {
                throw new NoDataFoundException("No existe el producto con ID: " + id);
            }
            repository.deleteById(id);
            return true;
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al eliminar el producto", e);
        }
    }
	
}
