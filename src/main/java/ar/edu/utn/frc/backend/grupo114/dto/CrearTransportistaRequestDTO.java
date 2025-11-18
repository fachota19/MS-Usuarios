package ar.edu.utn.frc.backend.grupo114.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearTransportistaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String telefono;

    @Email(message = "El email no tiene un formato válido")
    private String email;

    /**
     * Id de Usuario al que se asocia este transportista.
     * Puede ser null si se permiten transportistas sin usuario asociado.
     */
    private Long usuarioId;
}
