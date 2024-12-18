package com.restaurante.facturacion.productos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.restaurante.facturacion.productos.entity.Empleado;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.EmpleadoRepository;
import com.restaurante.facturacion.productos.service.EmpleadoService;
import com.restaurante.facturacion.productos.validator.EmpleadoValidator;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	@Autowired
    private EmpleadoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll(Pageable page) {
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
	public Empleado findById(int id) {
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
	public Empleado findByNombre(String nombre) {
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
	public Empleado create(Empleado obj) {
	    try {
	        EmpleadoValidator.validate(obj);
	        return repository.save(obj); 
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al crear el empleado", e);
	    }
	}

	@Override
	@Transactional
	public Empleado update(Empleado obj) {
	    try {
	    	EmpleadoValidator.validate(obj); 
	        if (repository.existsById(obj.getId())) { 
	            return repository.save(obj); 
	        } else {
	            throw new NoDataFoundException("No existe el empleado con ID: " + obj.getId());
	        }
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        // Excepción genérica
	        throw new GeneralServiceException("Error al actualizar el empleado", e);
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
	            throw new NoDataFoundException("No existe el empleado con ID: " + id);
	        }
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al eliminar el empleado", e);
	    }
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findByNombreContaining(String nombre) {
	    try {
	        List<Empleado> empleados = repository.findByNombreContaining(nombre);
	        if (empleados.isEmpty()) {
	            throw new NoDataFoundException("No se encontraron empleados que contengan el nombre: " + nombre);
	        }
	        return empleados;
	    } catch (ValidateServiceException | NoDataFoundException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new GeneralServiceException("Error al buscar empleados por nombre", e);
	    }
	}

	
}