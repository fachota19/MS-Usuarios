package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;

    // id del Usuario asociado (puede ser null)
    private Long usuarioId;
}
