package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Long usuarioId; // ID del usuario vinculado
    private String username; // Nombre del usuario vinculado
}