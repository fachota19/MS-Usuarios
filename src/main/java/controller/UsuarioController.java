package ar.edu.utn.frc.backend.grupo114.controller;

// DTOs
import ar.edu.utn.frc.backend.grupo114.dto.*;

// Service
import ar.edu.utn.frc.backend.grupo114.service.UsuarioService;

// Imports de Spring y Java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// ¡Ojo! No definimos un @RequestMapping base
// porque tus endpoints (usuarios, clientes, transportistas) son raíces diferentes.
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Inyección de dependencias del servicio
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // --- Endpoints de Usuario ---

    /**
     * Endpoint para GET /usuarios
     * Lista todos los usuarios registrados.
     */
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios); // 200 OK
    }

    /**
     * Endpoint para POST /usuarios
     * Crea un nuevo usuario dentro del sistema.
     */
    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody CrearUsuarioRequestDTO request) {
        UsuarioDTO nuevoUsuario = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario); // 201 Created
    }

    // --- Endpoints de Cliente ---

    /**
     * Endpoint para GET /clientes
     * Lista todos los clientes.
     */
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> getClientes() {
        List<ClienteDTO> clientes = usuarioService.getAllClientes();
        return ResponseEntity.ok(clientes); // 200 OK
    }

    /**
     * Endpoint para POST /clientes
     * Crea un cliente y lo vincula con un usuario existente.
     */
    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody CrearClienteRequestDTO request) {
        ClienteDTO nuevoCliente = usuarioService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente); // 201 Created
    }

    // --- Endpoints de Transportista ---

    /**
     * Endpoint para GET /transportistas
     * Devuelve la lista de transportistas.
     */
    @GetMapping("/transportistas")
    public ResponseEntity<List<TransportistaDTO>> getTransportistas() {
        List<TransportistaDTO> transportistas = usuarioService.getAllTransportistas();
        return ResponseEntity.ok(transportistas); // 200 OK
    }

    /**
     * Endpoint para POST /transportistas
     * Crea un transportista y lo vincula con un usuario existente.
     */
    @PostMapping("/transportistas")
    public ResponseEntity<TransportistaDTO> crearTransportista(@RequestBody CrearTransportistaRequestDTO request) {
        TransportistaDTO nuevoTransportista = usuarioService.crearTransportista(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTransportista); // 201 Created
    }
}