package com.restaurante.facturacion.productos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.facturacion.productos.entity.Cliente;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.ClienteRepository;
import com.restaurante.facturacion.productos.service.ClienteService;
import com.restaurante.facturacion.productos.validator.ClienteValidator;

import lombok.extern.slf4j.Slf4j;


@Slf4j

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
    private ClienteRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(int id) {
		try {
			return repository.findById(id).orElseThrow();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findByNombre(String nombre) {
		try {
			return repository.findByNombre(nombre);
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public Cliente create(Cliente obj) {
	    try {
	        ClienteValidator.validate(obj);
	        return repository.save(obj); 
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al crear el cliente", e);
	    }
	}

	@Override
	public Cliente update(Cliente obj) {
	    try {
	        ClienteValidator.validate(obj); 
	        if (repository.existsById(obj.getId())) { 
	            return repository.save(obj); 
	        } else {
	            throw new NoDataFoundException("No existe el cliente con ID: " + obj.getId());
	        }
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        // Excepciones específicas
	        throw e;
	    } catch (Exception e) {
	        // Excepción genérica
	        throw new GeneralServiceException("Error al actualizar el cliente", e);
	    }
	}

	@Override
	public boolean delete(int id) {
	    try {
	        if (repository.existsById(id)) { 
	            repository.deleteById(id);
	            return true;
	        } else {
	            throw new NoDataFoundException("No existe el cliente con ID: " + id);
	        }
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al eliminar el cliente", e);
	    }
	}


}
