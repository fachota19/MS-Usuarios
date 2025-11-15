package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private String email;
    private String tipoRolNombre; 
    private String keycloakId;
}