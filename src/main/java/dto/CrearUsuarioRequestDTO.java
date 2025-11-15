package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class CrearUsuarioRequestDTO {
    private String username;
    private String email;
    
    
    private String tipoRolNombre;
}