package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearUsuarioRequestDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String rol;
}
