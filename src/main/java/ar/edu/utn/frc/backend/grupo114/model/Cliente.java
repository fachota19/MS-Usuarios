package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 50)
    private String telefono;

    /**
     * Relación 0..1 con Usuario (un cliente puede o no tener usuario asociado).
     */
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
