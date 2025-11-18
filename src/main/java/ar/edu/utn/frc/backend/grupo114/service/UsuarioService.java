package ar.edu.utn.frc.backend.grupo114.service;

import ar.edu.utn.frc.backend.grupo114.dto.*;
import java.util.List;

public interface UsuarioService {
    
    // USUARIOS
    UsuarioDTO crearUsuario(CrearUsuarioRequestDTO request);
    UsuarioDTO obtenerUsuario(Long id);
    List<UsuarioDTO> listarUsuarios();
    
    // CLIENTES
    ClienteDTO crearCliente(CrearClienteRequestDTO request);
    ClienteDTO obtenerCliente(Long id);
    List<ClienteDTO> listarClientes();
    
    // TRANSPORTISTAS
    TransportistaDTO crearTransportista(CrearTransportistaRequestDTO request);
    TransportistaDTO obtenerTransportista(Long id);
    List<TransportistaDTO> listarTransportistas();
}