package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_rol")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
