package com.restaurante.facturacion.productos.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "productos")
@EntityListeners(AuditingEntityListener.class)
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", unique = true, nullable = false, length = 50)
	private String nombre;
	@Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;
	private Double precioUnitario;
	private int stock;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="marca_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hablder"})
    private Marca marca;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tiposProductos_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hablder"})
    private TipoProducto tipoProducto;
	
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="update_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date updateAt;
}
