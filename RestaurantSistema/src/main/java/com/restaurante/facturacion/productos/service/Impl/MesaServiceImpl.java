package com.restaurante.facturacion.productos.service.Impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restaurante.facturacion.productos.entity.Categoria;
import com.restaurante.facturacion.productos.entity.Empleado;
import com.restaurante.facturacion.productos.entity.Mesa;
import com.restaurante.facturacion.productos.exception.GeneralServiceException;
import com.restaurante.facturacion.productos.exception.NoDataFoundException;
import com.restaurante.facturacion.productos.exception.ValidateServiceException;
import com.restaurante.facturacion.productos.repository.MesaRepository;
import com.restaurante.facturacion.productos.service.MesaService;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    
    @Override
    @Transactional(readOnly = true)
    public List<Mesa> getMesasPorNumero(int numero, int page, int size) {
        try {
            return mesaRepository.findByNumero(numero, PageRequest.of(page, size));
        } catch (GeneralServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Mesa createMesa(Mesa mesa) {
        try {
            mesa.setCreatedAt(new Date());
            mesa.setUpdatedAt(new Date());
            return mesaRepository.save(mesa);
        } catch (GeneralServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Mesa updateMesa(int id, Mesa mesaDetails) {
        try {
            Mesa mesa = mesaRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("Mesa no encontrada"));
            mesa.setNumero(mesaDetails.getNumero());
            mesa.setCapacidad(mesaDetails.getCapacidad());
            mesa.setEstado(mesaDetails.getEstado());
            mesa.setUpdatedAt(new Date());
            return mesaRepository.save(mesa);
        } catch (GeneralServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteMesa(int id) {
        try {
            Mesa mesa = mesaRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("Mesa no encontrada"));
            mesaRepository.delete(mesa);
        } catch (GeneralServiceException | NoDataFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

	@Override
	@Transactional(readOnly=true)
	public List<Mesa> findAll(Pageable page) {
		try {
			return mesaRepository.findAll(page).toList();
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
	


}