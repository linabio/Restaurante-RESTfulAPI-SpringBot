package com.restaurante.facturacion.productos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurante.facturacion.productos.entity.Pedido;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.PedidoRepository;
import com.restaurante.facturacion.productos.service.PedidoService;
import com.restaurante.facturacion.productos.validator.PedidoValidator;

@Service
public class PedidoServiceImpl implements PedidoService{
	@Autowired
	public PedidoRepository repository;

	@Override
    @Transactional(readOnly = true)
    public List<Pedido> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al obtener todos los pedidos", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido findById(int id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No se encontró el pedido con ID: " + id));
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al buscar el pedido por ID", e);
        }
    }

    @Override
    @Transactional
    public Pedido create(Pedido obj) {
        try {
            PedidoValidator.validate(obj);
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al crear el pedido", e);
        }
    }

    @Override
    @Transactional
    public Pedido update(Pedido obj) {
        try {
            PedidoValidator.validate(obj);
            if (!repository.existsById(obj.getId())) {
                throw new NoDataFoundException("No existe el pedido con ID: " + obj.getId());
            }
            return repository.save(obj);
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al actualizar el pedido", e);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        try {
            if (!repository.existsById(id)) {
                throw new NoDataFoundException("No existe el pedido con ID: " + id);
            }
            repository.deleteById(id);
            return true;
        } catch (ValidateServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException("Error al eliminar el pedido", e);
        }
    }

}
