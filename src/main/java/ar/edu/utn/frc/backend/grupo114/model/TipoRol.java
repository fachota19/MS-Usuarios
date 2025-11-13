package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_roles")
@Data
public class TipoRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // Ej: CLIENTE, TRANSPORTISTA

    @Column
    private String descripcion;
}