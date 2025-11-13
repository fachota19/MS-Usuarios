package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class CrearUsuarioRequestDTO {
    private String username;
    private String email;
    // Usamos el nombre del rol (ej: "CLIENTE", "TRANSPORTISTA")
    // Es más fácil para el operador que saber el ID.
    private String tipoRolNombre;
}