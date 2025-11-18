package ar.edu.utn.frc.backend.grupo114.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearUsuarioRequestDTO {

    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    /**
     * Rol de negocio del usuario:
     * CLIENTE / TRANSPORTISTA / OPERADOR
     */
    @NotBlank(message = "El rol es obligatorio")
    private String rol;

    /**
     * Id de usuario en Keycloak.
     * Por ahora puede ser opcional (nullable) si aún no integraron seguridad.
     */
    private String keycloakId;
}
