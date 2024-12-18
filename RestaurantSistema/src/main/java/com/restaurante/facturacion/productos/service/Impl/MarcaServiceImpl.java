package com.restaurante.facturacion.productos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.facturacion.productos.entity.Marca;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.MarcaRepository;
import com.restaurante.facturacion.productos.service.MarcaService;
import com.restaurante.facturacion.productos.validator.MarcaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository repository;

    @Override
	@Transactional(readOnly=true)
	public List<Marca> findAll(Pageable page) {
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
	public Marca findById(int id) {
		try {
			return repository.findById(id).orElseThrow();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	
	@Override
	public Marca findByNombre(String nombre) {
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
    public Marca create(Marca obj) {
        try {
            MarcaValidator.validate(obj); // Validar datos antes de guardar
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e; // Re-lanzar excepciones específicas
        } catch (Exception e) {
            log.error("Error al crear la marca", e);
            throw new GeneralServiceException("Error al crear la marca", e);
        }
    }

    @Override
    @Transactional
    public Marca update(Marca obj) {
        try {
        	MarcaValidator.validate(obj); // Validar datos antes de actualizar
            if (!repository.existsById(obj.getId())) {
                throw new NoDataFoundException("No existe la marca con ID: " + obj.getId());
            }
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e; // Re-lanzar excepciones específicas
        } catch (Exception e) {
            log.error("Error al actualizar la marca", e);
            throw new GeneralServiceException("Error al actualizar la marca", e);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        try {
            if (!repository.existsById(id)) {
                throw new NoDataFoundException("No existe la marca con ID: " + id);
            }
            repository.deleteById(id);
            return true;
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e; // Re-lanzar excepciones específicas
        } catch (Exception e) {
            log.error("Error al eliminar la marca", e);
            throw new GeneralServiceException("Error al eliminar la marca", e);
        }
    }
}
