package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(name = "keycloak_id", length = 100)
    private String keycloakId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_rol_id", nullable = false)
    private TipoRol rol;
}
