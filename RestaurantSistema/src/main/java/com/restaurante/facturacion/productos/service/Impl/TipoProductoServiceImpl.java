package com.restaurante.facturacion.productos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.facturacion.productos.entity.TipoProducto;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.TipoProductoRepository;
import com.restaurante.facturacion.productos.service.TipoProductoService;
import com.restaurante.facturacion.productos.validator.TipoProductoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    @Autowired
    private TipoProductoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoProducto> findAll(Pageable page) {
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
    public TipoProducto findById(int id) {
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
    public TipoProducto findByNombre(String nombre) {
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
    public TipoProducto create(TipoProducto obj) {
    	try {
	        TipoProductoValidator.validate(obj);
	        return repository.save(obj); 
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al crear un tipo de producto", e);
	    }
    }

    @Override
    @Transactional
    public TipoProducto update(TipoProducto obj) {
    	 try {
 	        TipoProductoValidator.validate(obj); 
 	        if (repository.existsById(obj.getId())) { 
 	            return repository.save(obj); 
 	        } else {
 	            throw new NoDataFoundException("No existe el tipo de producto con ID: " + obj.getId());
 	        }
 	    } catch (ValidateServiceException | NoDataFoundException e) {
 	        throw e;
 	    } catch (Exception e) {
 	        throw new GeneralServiceException("Error al actualizar el tipo de producto", e);
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
 	            throw new NoDataFoundException("No existe el tipo de producto con ID: " + id);
 	        }
 	    } catch (ValidateServiceException | NoDataFoundException e) {
 	        throw e;
 	    } catch (Exception e) {
 	        throw new GeneralServiceException("Error al eliminar el tipo de producto", e);
 	    }
    }
}
