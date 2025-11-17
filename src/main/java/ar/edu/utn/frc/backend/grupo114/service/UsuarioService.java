package ar.edu.utn.frc.backend.grupo114.service;

import ar.edu.utn.frc.backend.grupo114.dto.*;
import ar.edu.utn.frc.backend.grupo114.exception.ResourceNotFoundException;
import ar.edu.utn.frc.backend.grupo114.model.*;
import ar.edu.utn.frc.backend.grupo114.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final TransportistaRepository transportistaRepository;
    private final TipoRolRepository tipoRolRepository;

    // ============================
    // USUARIO
    // ============================

    public UsuarioDTO crearUsuario(CrearUsuarioRequestDTO request) {

    TipoRol rol = tipoRolRepository.findByNombre(request.getRol())
            .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado: " + request.getRol()));

    Usuario usuario = Usuario.builder()
            .nombre(request.getNombre())
            .apellido(request.getApellido())
            .email(request.getEmail())
            .rol(rol)
            .build();

    usuarioRepository.save(usuario);

    return UsuarioDTO.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .apellido(usuario.getApellido())
            .email(usuario.getEmail())
            .rol(usuario.getRol().getNombre())
            .build();
}


    public UsuarioDTO obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .rol(usuario.getRol().getNombre())
                .build();
    }

    // ============================
    // CLIENTE
    // ============================

    public ClienteDTO crearCliente(CrearClienteRequestDTO request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Cliente cliente = Cliente.builder()
                .id(usuario.getId())
                .razonSocial(request.getRazonSocial())
                .usuario(usuario)
                .build();

        clienteRepository.save(cliente);

        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .razonSocial(cliente.getRazonSocial())
                .build();
    }

    public ClienteDTO obtenerCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        Usuario usuario = cliente.getUsuario();

        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .razonSocial(cliente.getRazonSocial())
                .build();
    }

    // ============================
    // TRANSPORTISTA
    // ============================

    public TransportistaDTO crearTransportista(CrearTransportistaRequestDTO request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Transportista transportista = Transportista.builder()
                .id(usuario.getId())
                .patenteCamion(request.getPatenteCamion())
                .usuario(usuario)
                .build();

        transportistaRepository.save(transportista);

        return TransportistaDTO.builder()
                .id(transportista.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .patenteCamion(transportista.getPatenteCamion())
                .build();
    }

    public TransportistaDTO obtenerTransportista(Long id) {
        Transportista t = transportistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transportista no encontrado"));

        Usuario usuario = t.getUsuario();

        return TransportistaDTO.builder()
                .id(t.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .patenteCamion(t.getPatenteCamion())
                .build();
    }
}
