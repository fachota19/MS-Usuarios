package ar.edu.utn.frc.backend.grupo114.service.impl;

import ar.edu.utn.frc.backend.grupo114.dto.*;
import ar.edu.utn.frc.backend.grupo114.service.*;
import ar.edu.utn.frc.backend.grupo114.exception.ResourceNotFoundException;
import ar.edu.utn.frc.backend.grupo114.exception.UsuarioYaTieneClienteException;
import ar.edu.utn.frc.backend.grupo114.model.Cliente;
import ar.edu.utn.frc.backend.grupo114.model.TipoRol;
import ar.edu.utn.frc.backend.grupo114.model.Transportista;
import ar.edu.utn.frc.backend.grupo114.model.Usuario;
import ar.edu.utn.frc.backend.grupo114.repository.ClienteRepository;
import ar.edu.utn.frc.backend.grupo114.repository.TipoRolRepository;
import ar.edu.utn.frc.backend.grupo114.repository.TransportistaRepository;
import ar.edu.utn.frc.backend.grupo114.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final TransportistaRepository transportistaRepository;
    private final TipoRolRepository tipoRolRepository;

    // ===================== USUARIOS =====================

    @Override
    public UsuarioDTO crearUsuario(CrearUsuarioRequestDTO request) {
        TipoRol rol = tipoRolRepository.findByNombre(request.getRol())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró el rol con nombre: " + request.getRol()));

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .keycloakId(request.getKeycloakId())
                .rol(rol)
                .build();

        Usuario guardado = usuarioRepository.save(usuario);
        return mapToUsuarioDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con id: " + id));
        return mapToUsuarioDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToUsuarioDTO)
                .collect(Collectors.toList());
    }

    private UsuarioDTO mapToUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .rol(usuario.getRol() != null ? usuario.getRol().getNombre() : null)
                .build();
    }

    // ===================== CLIENTES =====================

    @Override
    @Transactional
    public ClienteDTO crearCliente(CrearClienteRequestDTO request) {

        // 1. Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        // 2. Verificar si ya tiene cliente asociado
        if (clienteRepository.findByUsuarioId(request.getUsuarioId()).isPresent()) {
                throw new UsuarioYaTieneClienteException("El usuario ya tiene un cliente asociado");
        }

        // 3. Crear el cliente
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setUsuario(usuario);

        clienteRepository.save(cliente);

        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                cliente.getEmail(),
                usuario.getId()
        );
   }


    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente no encontrado con id: " + id));
        return mapToClienteDTO(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream()
                .map(this::mapToClienteDTO)
                .collect(Collectors.toList());
    }

    private ClienteDTO mapToClienteDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .usuarioId(cliente.getUsuario() != null ? cliente.getUsuario().getId() : null)
                .build();
    }

    // ===================== TRANSPORTISTAS =====================

    @Override
    public TransportistaDTO crearTransportista(CrearTransportistaRequestDTO request) {
        Usuario usuario = null;
        if (request.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Usuario no encontrado con id: " + request.getUsuarioId()));
        }

        Transportista transportista = Transportista.builder()
                .nombre(request.getNombre())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .usuario(usuario)
                .build();

        Transportista guardado = transportistaRepository.save(transportista);
        return mapToTransportistaDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public TransportistaDTO obtenerTransportista(Long id) {
        Transportista transportista = transportistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Transportista no encontrado con id: " + id));
        return mapToTransportistaDTO(transportista);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransportistaDTO> listarTransportistas() {
        return transportistaRepository.findAll().stream()
                .map(this::mapToTransportistaDTO)
                .collect(Collectors.toList());
    }

    private TransportistaDTO mapToTransportistaDTO(Transportista t) {
        return TransportistaDTO.builder()
                .id(t.getId())
                .nombre(t.getNombre())
                .telefono(t.getTelefono())
                .email(t.getEmail())
                .usuarioId(t.getUsuario() != null ? t.getUsuario().getId() : null)
                .build();
    }
}
