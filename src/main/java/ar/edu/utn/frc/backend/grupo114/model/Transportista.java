package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transportistas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transportista")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 50)
    private String telefono;

    @Column(length = 255)
    private String email;

    /**
     * Relación 0..1 con Usuario (un transportista puede o no tener usuario asociado).
     */
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
