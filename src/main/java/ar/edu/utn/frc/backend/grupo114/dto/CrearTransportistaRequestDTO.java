package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearTransportistaRequestDTO {

    private Long usuarioId;
    private String patenteCamion;
}
