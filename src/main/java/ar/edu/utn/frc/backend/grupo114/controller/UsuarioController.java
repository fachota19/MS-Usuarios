package ar.edu.utn.frc.backend.grupo114.controller;

import ar.edu.utn.frc.backend.grupo114.dto.*;
import ar.edu.utn.frc.backend.grupo114.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // ===================== USUARIOS =====================

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> crearUsuario(
            @Valid @RequestBody CrearUsuarioRequestDTO request) {
        UsuarioDTO creado = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // ===================== CLIENTES =====================

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(usuarioService.listarClientes());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerCliente(id));
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> crearCliente(
            @Valid @RequestBody CrearClienteRequestDTO request) {
        ClienteDTO creado = usuarioService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // ===================== TRANSPORTISTAS =====================

    @GetMapping("/transportistas")
    public ResponseEntity<List<TransportistaDTO>> listarTransportistas() {
        return ResponseEntity.ok(usuarioService.listarTransportistas());
    }

    @GetMapping("/transportistas/{id}")
    public ResponseEntity<TransportistaDTO> obtenerTransportista(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerTransportista(id));
    }

    @PostMapping("/transportistas")
    public ResponseEntity<TransportistaDTO> crearTransportista(
            @Valid @RequestBody CrearTransportistaRequestDTO request) {
        TransportistaDTO creado = usuarioService.crearTransportista(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
