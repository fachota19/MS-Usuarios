package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.Data;

@Data
public class CrearClienteRequestDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    // ID del usuario (creado previamente) al que se vinculará este cliente
    private Long usuarioId;
}