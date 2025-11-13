package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class TransportistaDTO {
    private Long id; // Este es el id_transportista
    private String nombre;
    private String email;
    private String telefono;
    private Long usuarioId; // ID del usuario vinculado
    private String username; // Nombre del usuario vinculado
}