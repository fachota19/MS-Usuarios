package ar.edu.utn.frc.backend.grupo114.service;

import ar.edu.utn.frc.backend.grupo114.dto.*;

import ar.edu.utn.frc.backend.grupo114.model.Cliente;
import ar.edu.utn.frc.backend.grupo114.model.TipoRol;
import ar.edu.utn.frc.backend.grupo114.model.Transportista;
import ar.edu.utn.frc.backend.grupo114.model.Usuario;

import ar.edu.utn.frc.backend.grupo114.repository.ClienteRepository;
import ar.edu.utn.frc.backend.grupo114.repository.TipoRolRepository;
import ar.edu.utn.frc.backend.grupo114.repository.TransportistaRepository;
import ar.edu.utn.frc.backend.grupo114.repository.UsuarioRepository;

import ar.edu.utn.frc.backend.grupo114.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final TransportistaRepository transportistaRepository;
    private final TipoRolRepository tipoRolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          ClienteRepository clienteRepository,
                          TransportistaRepository transportistaRepository,
                          TipoRolRepository tipoRolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
        this.transportistaRepository = transportistaRepository;
        this.tipoRolRepository = tipoRolRepository;
    }

    

    
    @Transactional(readOnly = true)
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirAUsuarioDTO)
                .collect(Collectors.toList());
    }

    
    @Transactional
    public UsuarioDTO crearUsuario(CrearUsuarioRequestDTO request) {
        
        TipoRol rol = tipoRolRepository.findByNombre(request.getTipoRolNombre())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el rol: " + request.getTipoRolNombre()));

        
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(request.getUsername());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setTipoRol(rol);
        

        
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        return convertirAUsuarioDTO(usuarioGuardado);
    }

    

    
    @Transactional(readOnly = true)
    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }

    
    @Transactional
    public ClienteDTO crearCliente(CrearClienteRequestDTO request) {
        
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con ID: " + request.getUsuarioId()));

        

        
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(request.getNombre());
        nuevoCliente.setApellido(request.getApellido());
        nuevoCliente.setEmail(request.getEmail());
        nuevoCliente.setTelefono(request.getTelefono());
        nuevoCliente.setUsuario(usuario);

        
        Cliente clienteGuardado = clienteRepository.save(nuevoCliente);
        return convertirAClienteDTO(clienteGuardado);
    }

    

    
    @Transactional(readOnly = true)
    public List<TransportistaDTO> getAllTransportistas() {
        return transportistaRepository.findAll().stream()
                .map(this::convertirATransportistaDTO)
                .collect(Collectors.toList());
    }

    
    @Transactional
    public TransportistaDTO crearTransportista(CrearTransportistaRequestDTO request) {
        
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con ID: " + request.getUsuarioId()));

        

        
        Transportista nuevoTransportista = new Transportista();
        nuevoTransportista.setNombre(request.getNombre());
        nuevoTransportista.setEmail(request.getEmail());
        nuevoTransportista.setTelefono(request.getTelefono());
        nuevoTransportista.setUsuario(usuario);

        
        Transportista transportistaGuardado = transportistaRepository.save(nuevoTransportista);
        return convertirATransportistaDTO(transportistaGuardado);
    }


    

    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setEmail(usuario.getEmail());
        dto.setKeycloakId(usuario.getKeycloakId());
        if (usuario.getTipoRol() != null) {
            dto.setTipoRolNombre(usuario.getTipoRol().getNombre());
        }
        return dto;
    }

    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        if (cliente.getUsuario() != null) {
            dto.setUsuarioId(cliente.getUsuario().getId());
            dto.setUsername(cliente.getUsuario().getUsername());
        }
        return dto;
    }

    private TransportistaDTO convertirATransportistaDTO(Transportista transportista) {
        TransportistaDTO dto = new TransportistaDTO();
        dto.setId(transportista.getId());
        dto.setNombre(transportista.getNombre());
        dto.setEmail(transportista.getEmail());
        dto.setTelefono(transportista.getTelefono());
        if (transportista.getUsuario() != null) {
            dto.setUsuarioId(transportista.getUsuario().getId());
            dto.setUsername(transportista.getUsuario().getUsername());
        }
        return dto;
    }
}