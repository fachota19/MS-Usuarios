package ar.edu.utn.frc.backend.grupo114.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    // nombre técnico de usuario del sistema
    private String username;

    private String email;

    // nombre del rol de negocio: CLIENTE / TRANSPORTISTA / OPERADOR
    private String rol;
}
