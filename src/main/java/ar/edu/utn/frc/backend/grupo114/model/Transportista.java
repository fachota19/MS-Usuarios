package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transportistas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patenteCamion;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
