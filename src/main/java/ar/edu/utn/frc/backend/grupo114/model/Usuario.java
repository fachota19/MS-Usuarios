package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(name = "keycloak_id")
    private String keycloakId;

    @ManyToOne
    @JoinColumn(name = "tipo_rol_id", nullable = false)
    private TipoRol tipoRol;
}