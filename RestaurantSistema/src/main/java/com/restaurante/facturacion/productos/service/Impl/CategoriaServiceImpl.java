package com.restaurante.facturacion.productos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.facturacion.productos.entity.Categoria;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.CategoriaRepository;
import com.restaurante.facturacion.productos.service.CategoriaService;
import com.restaurante.facturacion.productos.validator.CategoriaValidator;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CategoriaServiceImpl implements CategoriaService{
	
	@Autowired
	private CategoriaRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Categoria> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

		

	@Override
	@Transactional(readOnly=true)
	public Categoria findById(int id) {
		try {
			return repository.findById(id).orElseThrow();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}


	@Override
	public Categoria findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}

	}

	
	@Override
	@Transactional
	public Categoria save(Categoria obj) {
		try {
			CategoriaValidator.save(obj);
			if(obj.getId() == 0) {
				Categoria registroNuevo = repository.save(obj);
				return registroNuevo;
			}
			Categoria registroExiste = findById(obj.getId());
			registroExiste.setNombre(obj.getNombre());
			return repository.save(registroExiste);
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}

	}
	
	@Override
	@Transactional
	public boolean delete(int id) {
	    try {
	        if (repository.existsById(id)) { 
	            repository.deleteById(id);
	            return true;
	        } else {
	            throw new NoDataFoundException("No existe el Categoria con ID: " + id);
	        }
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al eliminar el Categoria", e);
	    }
	}
	
		
	
}