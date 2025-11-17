package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;     
    private String razonSocial;
}

