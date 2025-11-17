package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearClienteRequestDTO {

    private Long usuarioId;
    private String razonSocial;
}
