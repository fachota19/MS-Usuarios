package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class CrearTransportistaRequestDTO {
    private String nombre;
    private String email;
    private String telefono;

    
    private Long usuarioId;
}