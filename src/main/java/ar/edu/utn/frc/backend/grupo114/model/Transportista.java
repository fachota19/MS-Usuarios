package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "transportistas")
@Data
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transportista")
    private Long id;

    @Column
    private String nombre;

    @Column
    private String telefono;

    @Column
    private String email;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}