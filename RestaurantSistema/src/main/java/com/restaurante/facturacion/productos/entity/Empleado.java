package com.restaurante.facturacion.productos.entity;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
@Table(name = "empleados")
@EntityListeners(AuditingEntityListener.class)
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false, length = 15)
    private String documento;

    @Column(nullable = true, length = 15)
    private String telefono;

    @Column(nullable = true, length = 100)
    private String direccion;

    @Column(nullable = true, length = 50)
    private String email;

    // Relación ManyToOne con la entidad TipoEmpleado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_empleado", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoEmpleado tipoEmpleado;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}