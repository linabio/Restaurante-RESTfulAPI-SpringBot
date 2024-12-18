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
@Table(name = "pedidos")
@EntityListeners(AuditingEntityListener.class)
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int estado;
	private Double total;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hablder"})
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empleado_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hablder"})
	private Empleado empleado;
	
	@Column(name="created_at",nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="update_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date updateAt;
}
