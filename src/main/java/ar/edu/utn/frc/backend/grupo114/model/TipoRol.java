package ar.edu.utn.frc.backend.grupo114.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false, length = 50)
    private String nombre;

    // opcional, pero útil para documentación
    @Column(length = 255)
    private String descripcion;
}
