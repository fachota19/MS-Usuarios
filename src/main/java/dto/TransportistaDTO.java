package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class TransportistaDTO {
    private Long id; 
    private String nombre;
    private String email;
    private String telefono;
    private Long usuarioId; 
    private String username; 
}